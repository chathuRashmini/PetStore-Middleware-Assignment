package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class PetResourceTest {
    /**
     * Tests the method getPets
     */
    @Test
    public void testGetPets() {
        given()
            .when().get("/v1/pets")
            .then()
            .statusCode(200);
    }

    /**
     * Tests the method getPetById
     */
    @Test
    public void testGetPetById() {
	    given().contentType(ContentType.JSON).when()
            .get("/v1/pets/getPetById?petId=3").then().statusCode(200)
            .body("petId", is(3))
            .body("petAge", notNullValue())
            .body("petName", notNullValue())
            .body("petType", notNullValue());
    }

    /**
     * Tests the method getPetByName
     */
    @Test
    public void testGetPetByName() {
        given().contentType(ContentType.JSON)
            .when().get("/v1/pets/getPetByName?petName=Timmy").then().statusCode(200)
            .body("petId", notNullValue())
            .body("petAge", notNullValue())
            .body("petName", notNullValue())
            .body("petType", notNullValue());
    }

    /**
     * Tests the method getPetByAge
     */
    @Test
    public void testGetPetByAge() {
        given().contentType(ContentType.JSON)
            .when().get("/v1/pets/getPetByAge?petAge=2").then().statusCode(200)
            .body("petId", notNullValue())
            .body("petAge", notNullValue())
            .body("petName", notNullValue())
            .body("petType", notNullValue());
    }

    /**
     * Tests the method getPetByType
     */
    @Test
    public void testGetPetByType() {
        given().contentType(ContentType.JSON)
            .when().get("/v1/pets/getPetByType?petType=Dog").then().statusCode(200)
            .body("petId", notNullValue())
            .body("petAge", notNullValue())
            .body("petName", notNullValue())
            .body("petType", notNullValue());
    }

    /**
     * Tests the method addPet
     */
    @Test
    public void testAddPet() {
        given().contentType(ContentType.JSON)
            .when().post("/v1/pets/addPet?petId=10&petName=Softie&petAge=1&petType=Cat")
            .then()
            .statusCode(200)
            .body("petId", is(10))
            .body("petAge", is(1))
            .body("petName", is("Softie"))
            .body("petType", is("Cat"));
    }

    /**
     * Tests the method addPetObject
     */
    @Test
    public void testAddPetObject() {
	    final String pet = "{\"petId\": 7, \"petAge\": 2, \"petName\": \"Dinky\", \"petType\": \"Dog\"}";

	    given().body(pet).contentType(ContentType.JSON)
            .when().post("/v1/pets/addPetObject")
            .then()
            .statusCode(200)
            .body("petId", is(7))
            .body("petAge", is(2))
            .body("petName", is("Dinky"))
            .body("petType", is("Dog"));
    }

    /**
     * Tests the method deletePetById
     */
    @Test
    public void testDeletePetById() {
        given()
            .when().delete("/v1/pets/delete?petId=2")
            .then()
            .statusCode(200);
    }

    /**
     * Tests the method updatePet
     */
    @Test
    public void testUpdatePet() {
        final String pet = "{\"petAge\": 2, \"petId\": 8, \"petName\": \"Timmy\", \"petType\": \"Dog\"}";

        given().body(pet).contentType(ContentType.JSON)
            .when().put("/v1/pets/updatePet")
            .then()
            .statusCode(200)
            .body("petId", is(8))
            .body("petAge", is(2))
            .body("petName", is("Timmy"))
            .body("petType", is("Dog"));
    }

    /**
     * Tests the method updatePetName
     */
    @Test
    public void testUpdatePetName() {
        given().when()
            .put("/v1/pets/updatePetName?petId=2&petName=Kitty")
            .then()
            .statusCode(200);
    }

    /**
     * Tests the method updatePetAge
     */
    @Test
    public void testUpdatePetAge() {
        given().when()
            .put("/v1/pets/updatePetAge?petId=2&petAge=1")
            .then()
            .statusCode(200);
    }

    /**
     * Tests the method updatePetType
     */
    @Test
    public void testUpdatePetType() {
        given()
            .when().put("/v1/pets/updatePetType?petId=2&petType=Dog")
            .then()
            .statusCode(200);
    }

    /**
     * Tests the method getPetTypes
     */
    @Test
    public void testGetPetTypes() {
        given()
            .when().get("/v1/pets/petTypes")
            .then()
            .statusCode(200);
    }

    /**
     * Tests the method getPetTypeById
     */
    @Test
    public void testGetPetTypeById() {
        given()
            .when().get("/v1/pets/petTypes/getPetTypeById?petTypeId=2")
            .then()
            .statusCode(200)
            .body("petType", notNullValue())
            .body("petTypeId", is(2));
    }

    /**
     * Tests the method deletePetType
     */
    @Test
    public void testDeletePetType() {
        given()
            .when().delete("/v1/pets/petTypes/deletePetType?petTypeId=1")
            .then()
            .statusCode(200);
    }

    /**
     * Tests the method updatePetTypeName
     */
    @Test
    public void testUpdatePetTypeName() {
        given()
            .when().put("/v1/pets/petTypes/updatePetTypeName?petTypeId=1&petType=Rabbit")
            .then()
            .statusCode(200);
    }
}