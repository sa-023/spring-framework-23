package com.company.controller;
import com.company.model.Organization;
import com.company.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.annotation.security.RolesAllowed;
/*
 * 🦋 Authentication & Authorization
 * · Authentication: Who are you?
 * · Authorization: What permissions do you have?
 *
 * 🔺 HTTP Basic Authentication
 * · With HTTP Basic authentication, the client needs to send the user credentials every time and authentication logic has to be
 *   executed every time with all the requests. With this approach we ended up sharing the credentials often over the network.
 *
 * 🔺 OAuth 2.0
 * · It is an authorization protocol and NOT an authentication protocol. OAuth = Open + Authorization.
 * · Industry Standard Protocol, developed for authorization between services or applications.
 * · The present version of this protocol is 2.0, hence the name OAuth 2.0.
 * · OAuth is also called a "Delegated Authorization" framework.
 * 🌀 OAuth2 Terminology (Components):
 * · Protected Resource, Resource Owner(User), Resource Server, Client(Application), Authorization Server.
 * ■ PROTECTED RESOURCE: Anything that needs to be accessed by an external service and needs authorization.
 *   Ex: Photos stored in a Google Drive account
 * ■ RESOURCE OWNER (User): The user or system that owns the protected resources and can grant access to them.
 *   Ex: Person who owns the photos.
 * ■ RESOURCE SERVER: A server that protects the user’s resources and receives access requests from the Client.
 *   It accepts and validates an Access Token from the Client and returns the appropriate resources to it.
 *   Ex: Google Driver Server, which stores our photos.
 * ■ CLIENT: The client is the system that requires access to the protected resources. To access resources, the Client must hold the appropriate Access Token.
 *   Ex: Image Gallery App. The client can be a web, mobile, or desktop application, or it can be a standalone service like a microservice, etc.
 * ■ AUTHORIZATION SERVER: The server that authorizes the client to access the user resources in the resource server.
 *   Ex: AWS Cognito, Microsoft Azure AD, Google Identity Platform, OKTA. Key Cloak, Spring Authorization Server.
 * 🌀 OAuth2 Flow:
 * 1. Client - Authentication Request --> Resource Owner
 * 2. Client <-- Authentication Grant - Resource Owner
 * 3. Client - Authentication Grant --> Authentication Server
 * 4. Client <-- Access Token - Authentication Server
 * 5. Client - Access Token --> Resource Server
 * 6. Client <-- Protected resource - Resource Server
 *
 * 1. The application requests authorization to access service resources from the user.
 * 2. If the user authorized the request, the application receives an authorization grant.
 * 3. The application requests an access token from the Authorization Server by presenting authentication of its own identity, and the authorization grant.
 * 4. If the application identity is authenticated and the authorization grant is valid, the Authorization Server issues
 *    an access token to the application. Authorization is complete.
 * 5. The application requests the resource from the Resource Server and presents the access token for authentication.
 * 6. If the access token is valid, the Resource Server serves the resource to the application.
 *
 * 🦋 KeyCloak AUTHORIZATION Server
 * · It is an open source identity and access management product.
 * · Using KeyCloak, we can easily add an authentication and authorization to our Web Applications with the minimum effort.
 *
 * 1. REALM: It is a space where you manage objects, including users, applications, roles, and groups.
 * 2. CLIENT: Clients in Keycloak are entities that can request user authentication.
 *    The clients are often the applications or services that we want to secure by providing a single sign-on solution.
 * 3. KEYCLOAK ROLES:
 *    · Realm Role: It is a global role, belonging to that specific realm. You can access it from any client and map to any user. Ex: Admin.
 *    · Client Role: It is a role which belongs only to that specific client. You can not access that role from a different client.
 *      You can only map it to the Users from that client. Ex: Employee, Manager.
 *    · Composite Roles: It is a role that has one or more roles (realm or client ones) associated to it.
 *
 * 🦋 Adding Role Based Security
 * · Spring Security provides support for JSR-250 annotation security. That means we can use javax.annotation.security.RolesAllowed
 *   in the place of Spring’s @Secured annotation. Ex: @RolesAllowed({ "ADMIN", "USER" })
 *
 */
@RestController
@RequestMapping(value = "/v1/organization")
public class OrganizationController {
    private final OrganizationService organizationService;
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }


    @RolesAllowed({"Admin","User"})
    @GetMapping("/{organizationId}")
    public ResponseEntity<Organization> getOrganization(@PathVariable("organizationId") Long organizationId) throws Exception {
        return ResponseEntity.ok(organizationService.findById(organizationId));
    }

    @RolesAllowed({"Admin","User"})
    @PostMapping
    public ResponseEntity<Organization> createOrganization(@RequestBody Organization organization) {
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationService.create(organization));
    }

    @RolesAllowed({"Admin"})
    @DeleteMapping("/{organizationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLicense(@PathVariable("organizationId") Long organizationId) {
        organizationService.delete(organizationId);
    }

}
