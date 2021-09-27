// tag::copyright[]
/*******************************************************************************
 * Copyright (c) 2018 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - Initial implementation
 *******************************************************************************/
// end::copyright[]
package ibm.openliberty.rdp.gestsv2.resources;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ibm.openliberty.rdp.gestsv2.models.Gestsv2Record;
import ibm.openliberty.rdp.gestsv2.service.Gestsv2Service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;



@RequestScoped
@RolesAllowed("Role1")
@Tag(name = "Gestsv2 Record", description = "Creating, retriving, updating or deleting record and view options of connection")
@Path("gestsv2")
public class Gestsv2Resource {

    @Inject
    private Gestsv2Service gestsv2service;

    /**
     * This method returns the existing/stored last 3 records in Json format
     */

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @APIResponses({
        @APIResponse(
            responseCode = "200",
            description = "Successfully retrived list of Gestsv2 Records."),
        @APIResponse(
            responseCode = "500",
            description = "Server error.") })
    @Operation(summary = "Retrive list of last 5 Gestsv2 Records from database.")
    public List<Gestsv2Record> getGestsv2Record() {
    	return (List<Gestsv2Record>) gestsv2service.readLast5Gestsv2Records();
    }    
    
    /**
     * This method returns the existing/stored records in Json format
     */
    
    @GET
    @Path("{field}/{value}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @APIResponses({
        @APIResponse(
            responseCode = "200",
            description = "Successfully retrived Gestsv2 Records with provided data."),
        @APIResponse(
                responseCode = "404",
                description = "Not found Gestsv2 Records with provided data."),
        @APIResponse(
            responseCode = "500",
            description = "Server error.") })
    @Operation(summary = "Retrive Gestsv2 Records with provided data from database.")
    public Response getGestsv2Record(@PathParam("field") String field, @PathParam("value") String value) {
    	try {
    		String sql = "SELECT e FROM Gestsv2Record e WHERE e." + field + " = '" + value + "'";
	    	List<Gestsv2Record> gestsv2Record = gestsv2service.findQuerry(sql);
	        return Response.status(Response.Status.OK)
	        		.entity(gestsv2Record).build();
    		}
    	catch (NoResultException e) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("{\"message\":\"Gestsv2Record does not exist\"}").build();
        	}
    	catch (final Exception e) {
    		StringWriter sw = new StringWriter();
    		PrintWriter pw = new PrintWriter(sw);
    		e.printStackTrace(pw);
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(sw.toString() ).build();
    	}
    }  
    
    /**
     * This method creates a new record or updates existing
     */
    
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @APIResponses({
        @APIResponse(
            responseCode = "200",
            description = "Successfully updated Gestsv2 Record."),
        @APIResponse(
            responseCode = "201",
            description = "Successfully created Gestsv2 Record."),
        @APIResponse(
            responseCode = "500",
            description = "Server error.") })
    @Operation(summary = "Create or update Gestsv2 Record in the database (id and type are required feilds).")
    public Response addNewGestsv2Record(Gestsv2Record gestsv2Record) throws JsonProcessingException {
        
        	try {

        		// set timestamp for the record
            	gestsv2Record.setCreationTime(new Date());
            	
            	// form and set json string for the record
                ObjectMapper mapper = new ObjectMapper();
                DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss XXX z");
                mapper.setDateFormat(dateformat);
                mapper.setSerializationInclusion(Include.NON_NULL);
                String jsonValue = mapper.writeValueAsString(gestsv2Record);
                gestsv2Record.setJsonValue(jsonValue);
                
                // check by id if record already exists
                Gestsv2Record old = gestsv2service.findGestsv2Record(gestsv2Record.getId());
                
                // update by internalRecordId if record already exists
                gestsv2Record.setInternalRecordId(old.getInternalRecordId());
                gestsv2service.updateGestsv2Record(gestsv2Record, gestsv2Record.getInternalRecordId());
                
                return Response.status(Response.Status.OK)
                        .entity("{\"message\":\"Gestsv2Record has been updated\"}").build();
                } 
        	catch(NoResultException e){
        		// create record if it not exists
        		gestsv2service.createGestsv2Record(gestsv2Record);
                return Response.status(Response.Status.CREATED)
                		.entity("{\"message\":\"Gestsv2Record has been created\"}").build(); 
                }
        	catch (final Exception e) {
        		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                		.entity("{\"message\":\"Server error\"}").build();
        		}
    }

    /**
     * This method deletes a specific existing/stored record
     */
	
    @DELETE
    @Path("{id}")
    @Transactional
    @APIResponses({
        @APIResponse(
            responseCode = "200",
            description = "Successfully deleted Gestsv2 Record with provided id."),
        @APIResponse(
            responseCode = "404",
            description = "Not found Gestsv2 Record with provided id."),
        @APIResponse(
            responseCode = "500",
            description = "Server error.") })
    @Operation(summary = "Delete Gestsv2 Record by id from database.")
    public Response deleteGestsv2Record(@PathParam("id") String id) {
    	try {
	    	Gestsv2Record gestsv2Record = gestsv2service.findGestsv2Record(id);
	    	gestsv2service.deleteGestsv2Record(gestsv2Record);
	        return Response.status(Response.Status.OK).build();
    		}
    	catch(NoResultException e){
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Gestsv2Record does not exist").build();
        	}
    	catch (final Exception e) {
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Server error").build();
    	}
    }

    /**
     * This method return options of connection
     */
    
    @OPTIONS
    @APIResponses({
        @APIResponse(
            responseCode = "200",
            description = "Successfully retrived list of options."),
        @APIResponse(
            responseCode = "500",
            description = "Server error.") })
    @Operation(summary = "Return list of options.")
    public Response serveOptions(@Context HttpHeaders headers) {

      return Response.ok().
              header("Access-Control-Allow-Origin",getCorsOrigins(headers)).
              header("Access-Control-Allow-Methods","POST, OPTIONS, DELETE").
              header("Access-Control-Allow-Headers","Origin,Access-Control-Allow-Origin,Authorization,X-Requested-With,content-type").
              build();
    }
    
    /**
     * This method sets Access-Control-Allow-Origin header
     */
    
    protected static String getCorsOrigins(HttpHeaders headers){
        List<String> origins = new ArrayList<String>();

        List<String> headerOrigins = headers.getRequestHeader("Origin");

        if (headerOrigins != null) {
            origins.addAll(headerOrigins);
        }else{
            origins.add("*");
        }

        String corsOrigins = "";
        for (String s : origins) {
            if (corsOrigins.length() > 0) corsOrigins += " ";
            corsOrigins += s;
        }
        return corsOrigins;
    }
}
