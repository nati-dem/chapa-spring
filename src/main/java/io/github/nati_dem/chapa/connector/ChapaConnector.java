package io.github.nati_dem.chapa.connector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.nati_dem.chapa.exception.ChapaAPIException;
import io.github.nati_dem.chapa.model.VerifyResponseData;
import io.github.nati_dem.chapa.util.ChapaUtil;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * The <code>ChapaConnector</code> class is responsible for making GET and POST request to Chapa API to initialize
 * a transaction, verify a transaction and create a sub account.
 */
@Component
public class ChapaConnector {

	private final WebTarget chapaWebTarget;
	
    /**
     * @param chapaWebTarget Implementation of {@link WebTarget} interface.
     */
    @Autowired
    public ChapaConnector(WebTarget chapaWebTarget) {
        this.chapaWebTarget = chapaWebTarget;
    }

    /**
     * @param transactionRef A transaction reference which was associated
     *                       with tx_ref field in post data. This field uniquely
     *                       identifies a transaction.
     * @return An object of {@link VerifyResponseData} containing
     *        response data from Chapa API.
     * @throws ChapaAPIException Throws an exception for failed request to Chapa API.
     */
    public VerifyResponseData verify(String transactionRef) throws ChapaAPIException {
        if (!ChapaUtil.isNotNullAndEmpty(transactionRef)) {
            throw new IllegalArgumentException("Transaction reference can't be null or empty");
        }
        
        Invocation.Builder invocationBuilder = getInvocationBuilder("/transaction/verify/" + transactionRef);
        Response response = invocationBuilder.get(Response.class);
        VerifyResponseData responseBody = response.readEntity(VerifyResponseData.class);
        
        if(!ChapaUtil.isSuccessResponse(response.getStatus())) {
        	throw new ChapaAPIException(responseBody.getMessage());
        }
        return responseBody;
    }

    private Invocation.Builder getInvocationBuilder(String path){
        WebTarget verifyWebTarget = chapaWebTarget.path(path);
        // todo - add proper logging
        System.out.println(verifyWebTarget.toString());
        Invocation.Builder invocationBuilder = verifyWebTarget.request(MediaType.APPLICATION_JSON);
        return invocationBuilder;
    }
}
