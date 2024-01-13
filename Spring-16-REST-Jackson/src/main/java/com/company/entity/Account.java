package com.company.entity;
import com.company.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
/*
 * 🦋 Jackson JSON ObjectMapper
 * · When you create a @RestController in a SpringBoot application to define API endpoints then Jackson JSON ObjectMapper
 *   is default HTTP Converter of your REST application which does:
 * 1. Convert the incoming JSON Request Body to Java Object of your method @RequestBody argument. Generally used in POST HTTP methods.
 *    JSON to Java Object → Deserialization
 * 2. Convert the returned Java Object to JSON Response. Generally used in GET HTTP methods.
 *    Java Object to JSON → Serialization
 *
 * 🔺 Jackson Annotations
 * · @JsonIgnoreProperties: class level annotation that marks a property or a list of properties that Jackson will Ignore.
 * · @JsonIgnore: is used to mark a property to be ignored at the field level.
 * · @JsonInclude: is used to exclude properties with empty/null/default values.
 * · @JsonProperty: is used to indicate the property name in Json.
 * · @JsonNaming : is used to choose the naming strategies for properties in serialization, overriding the default.
 * · @JsonBackReference: is the back part of the reference; it will be omitted from serialization.
 * · @JsonManagedReference: is the forward part of the reference; it will be the one that gets serialized normally.
 *
 * 🖍️...
 * · When we have (fetch = FetchType.LAZY) in the class, JSON will create one empty field "hibernateLazyInitializer": {} in the response body.
 *   To prevent that, we use  the @JsonIgnoreProperties(value = "{hibernateLazyInitializer}", ignoreUnknown = true) annotation with the class.
 * · @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) : It will not show the data when retrieved; it will show the data when posted.
 * · @JsonIgnoreProperties(value = {"state", "postalCode"}, ignoreUnknown = true): is used at the class level to mark a property or list of properties to be ignored.
 * · ignoreUnknown = true: To ignore any unknown properties in JSON input without exception. Any property for which we don't have a
 *   corresponding field in the respective class will be ignored.
 *
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "account_details ")
//@JsonIgnoreProperties(value = {"state", "postalCode"}, ignoreUnknown = true)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"},ignoreUnknown = true)
public class Account extends BaseEntity {

    private String name;
    @JsonIgnore
    private String address;
    private String country;
    private String state;
    private String city;
    private Integer age;
    @Column(name = "postal_code")
    private String postalCode;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

    @OneToOne(mappedBy = "account")
    @JsonBackReference // is the back part of the reference; it will be omitted from serialization. We will not see user information in the account response body.
    private User user;


}
