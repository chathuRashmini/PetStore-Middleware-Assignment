package com.example.petstore;

import java.util.List;
import java.util.ArrayList;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/v1/pets")
@Produces("application/json")
public class PetResource {

	List<Pet> pets = new ArrayList<Pet>();
	List<PetType> petTypes = new ArrayList<PetType>();

	public PetResource() {
		Pet pet1 = new Pet();
		pet1.setPetId(1);
		pet1.setPetAge(4);
		pet1.setPetName("Timmy");
		pet1.setPetType("Dog");

		Pet pet2 = new Pet();
		pet2.setPetId(2);
		pet2.setPetAge(4);
		pet2.setPetName("Sophie");
		pet2.setPetType("Cat");

		Pet pet3 = new Pet();
		pet3.setPetId(3);
		pet3.setPetAge(2);
		pet3.setPetName("Peththappu");
		pet3.setPetType("Bird");

		Pet pet4 = new Pet();
		pet4.setPetId(4);
		pet4.setPetAge(2);
		pet4.setPetName("Rex");
		pet4.setPetType("Dog");

		pets.add(pet1);
		pets.add(pet2);
		pets.add(pet3);
		pets.add(pet4);

		PetType type1 = new PetType();
		type1.setPetTypeId(1);
		type1.setPetType("Dog");

		PetType type2 = new PetType();
		type2.setPetTypeId(2);
		type2.setPetType("Cat");

		PetType type3 = new PetType();
		type3.setPetTypeId(3);
		type3.setPetType("Parrot");

		petTypes.add(type1);
		petTypes.add(type2);
		petTypes.add(type3);
	}

	/**
	 * This method returns all the pets that are available at the pet store
	 * @return pets
	 */
	@APIResponses(value = {
			@APIResponse(
					responseCode = "200",
					description = "All Pets",
					content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))
			)
	})
	@GET
	public Response getPets() {
		return Response.ok(pets).build();
	}

	/**
	 * This method returns the pet for the given id
	 * @param petId
	 * @return foundedPet
	 */
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Display the Pet for the given id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.")})
	@GET
	@Path("getPetById")
	public Response getPetById(@QueryParam("petId") int petId) {
		if (petId < 0) {
			return Response.status(Status.NOT_FOUND).build();
		}

		Pet foundedPet = new Pet();

		pets.forEach(pet -> {
			if (pet.getPetId() == petId) {
				foundedPet.setPetId(pet.getPetId());
				foundedPet.setPetName(pet.getPetName());
				foundedPet.setPetType(pet.getPetType());
				foundedPet.setPetAge(pet.getPetAge());
			}
		});

		return Response.ok(foundedPet).build();
	}

	/**
	 * This method returns all the pets for the given name
	 * @param petName
	 * @return foundedPets
	 */
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Display the Pet for the given name", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.")})
	@GET
	@Path("getPetByName")
	public Response getPetByName(@QueryParam("petName") String petName) {

		List<Pet> foundedPets = new ArrayList<Pet>();

		pets.forEach(pet -> {
			if (pet.getPetName().equals(petName)) {
				Pet foundedPet = new Pet();
				foundedPet.setPetId(pet.getPetId());
				foundedPet.setPetName(pet.getPetName());
				foundedPet.setPetType(pet.getPetType());
				foundedPet.setPetAge(pet.getPetAge());

				foundedPets.add(foundedPet);
			}
		});

		return Response.ok(foundedPets).build();
	}

	/**
	 * This method returns all the pets for the given age
	 * @param petAge
	 * @return foundedPets
	 */
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Display the Pet for the given age", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.")})
	@GET
	@Path("getPetByAge")
	public Response getPetByAge(@QueryParam("petAge") int petAge) {
		if (petAge < 0) {
			return Response.status(Status.NOT_FOUND).build();
		}

		List<Pet> foundedPets = new ArrayList<Pet>();

		pets.forEach(pet -> {
			if (pet.getPetAge() == petAge) {
				Pet foundedPet = new Pet();
				foundedPet.setPetId(pet.getPetId());
				foundedPet.setPetName(pet.getPetName());
				foundedPet.setPetType(pet.getPetType());
				foundedPet.setPetAge(pet.getPetAge());
				foundedPets.add(foundedPet);
			}
		});

		return Response.ok(foundedPets).build();
	}

	/**
	 * This method returns all the pets for the given pet type
	 * @param petType
	 * @return foundedPets
	 */
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Display the Pet for the given age", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.")})
	@GET
	@Path("getPetByType")
	public Response getPetByType(@QueryParam("petType") String petType) {

		List<Pet> foundedPets = new ArrayList<Pet>();

		pets.forEach(pet -> {
			if (pet.getPetType().equals(petType)) {
				Pet foundedPet = new Pet();
				foundedPet.setPetId(pet.getPetId());
				foundedPet.setPetName(pet.getPetName());
				foundedPet.setPetType(pet.getPetType());
				foundedPet.setPetAge(pet.getPetAge());
				foundedPets.add(foundedPet);
			}
		});

		return Response.ok(foundedPets).build();
	}

	/**
	 * This method adds a new pet to the pet store, when attributes passed as query parameters
	 * @param petId
	 * @param petName
	 * @param petAge
	 * @param petType
	 * @return pet
	 */
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "New Pet", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
	})
	@POST
	@Path("addPet")
	public Response addPet(
			@QueryParam("petId") int petId,
			@QueryParam("petName") String petName,
			@QueryParam("petAge") int petAge,
			@QueryParam("petType") String petType
	) {
		if (petId < 0) {
			return Response.status(Status.NOT_FOUND).build();
		}

		Pet pet = new Pet();
		pet.setPetId(petId);
		pet.setPetAge(petAge);
		pet.setPetName(petName);
		pet.setPetType(petType);

		pets.add(pet);

		return Response.ok(pet).build();
	}

	/**
	 * This method adds the given pet object as a new pet to the pet store
	 * @param pet
	 * @return newPet
	 */
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "New Pet", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
	})
	@POST
	@Path("addPetObject")
	public Response addPetObject(@RequestBody Pet pet) {

		Pet newPet = new Pet();
		newPet.setPetId(pet.getPetId());
		newPet.setPetAge(pet.getPetAge());
		newPet.setPetName(pet.getPetName());
		newPet.setPetType(pet.getPetType());

		pets.add(newPet);

		return Response.ok(newPet).build();
	}

	/**
	 * This method deletes the pet of the given id
	 * @param petId
	 * @return pets
	 */
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Delete Pet at given id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.")
	})
	@DELETE
	@Path("delete")
	public Response deletePetById(@QueryParam("petId") int petId) {
		if (petId <= 0) {
			return Response.status(Status.NOT_FOUND).build();
		}

		pets.removeIf(availablePets -> availablePets.getPetId().equals(petId));

		return Response.ok(pets).build();
	}

	/**
	 * This method updates the pet at the given id
	 * @param pet
	 * @return pet
	 */
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Update Pet", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
	})
	@PUT
	@Path("updatePet")
	public Response updatePet(@RequestBody Pet pet) {

		pets.forEach(p -> {
			if (p.getPetId() == pet.getPetId()) {
				System.out.println(p.getPetId());
				if (pet.getPetName().length() == 0) {
					p.setPetName(pet.getPetName());
				}
				if (pet.getPetAge() != null) {
					p.setPetAge(pet.getPetAge());
				}
				if (pet.getPetType().length() == 0) {
					p.setPetType(pet.getPetType());
				}
			}
		});
		return Response.ok(pet).build();
	}

	/**
	 * This method updates the pet name of the pet at the given id
	 * @param petId
	 * @param petName
	 * @return pets
	 */
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Update Pet Name for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.")})
	@PUT
	@Path("updatePetName")
	public Response updatePetName(
			@QueryParam("petId") int petId,
			@QueryParam("petName") String petName
	) {
		if (petId < 0) {
			return Response.status(Status.NOT_FOUND).build();
		}

		pets.forEach(pet -> {
			if (pet.getPetId() == petId) {
				pet.setPetName(petName);
			}
		});
		return Response.ok(pets).build();
	}

	/**
	 * This method updates the pet age of the pet at the given id
	 * @param petId
	 * @param petAge
	 * @return pets
	 */
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Update age of the pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.")})
	@PUT
	@Path("updatePetAge")
	public Response updatePetAge(
			@QueryParam("petId") int petId,
			@QueryParam("petAge") int petAge
	) {
		if (petId < 0) {
			return Response.status(Status.NOT_FOUND).build();
		}

		pets.forEach(pet -> {
			if (pet.getPetId() == petId) {
				pet.setPetAge(petAge);
			}
		});
		return Response.ok(pets).build();
	}

	/**
	 * This method updates the pet type of the pet at the given id
	 * @param petId
	 * @param petType
	 * @return pets
	 */
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Update the type of the pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.")})
	@PUT
	@Path("updatePetType")
	public Response updatePetType(
			@QueryParam("petId") int petId,
			@QueryParam("petType") String petType
	) {
		if (petId < 0) {
			return Response.status(Status.NOT_FOUND).build();
		}

		pets.forEach(pet -> {
			if (pet.getPetId() == petId) {
				pet.setPetType(petType);
			}
		});
		return Response.ok(pets).build();
	}

	/**
	 * This method returns all the available pet types
	 * @return petTypes
	 */
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "All Pet Types", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet")))})
	@GET
	@Path("petTypes")
	public Response getPetTypes() {
		return Response.ok(petTypes).build();
	}

	/**
	 * This method returns the pet type at given id
	 * @param petTypeId
	 * @return foundPetType
	 */
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet Type for a given Id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet")))})

	@GET
	@Path("petTypes/getPetTypeById")
	public Response getPetTypeById(@QueryParam("petTypeId") int petTypeId) {
		if (petTypeId <= 0) {
			return Response.status(Status.NOT_FOUND).build();
		}

		PetType foundPetType = new PetType();

		petTypes.forEach(petType -> {
			if (petType.getPetTypeId().equals(petTypeId) ) {
				foundPetType.setPetTypeId(petType.getPetTypeId());
				foundPetType.setPetType(petType.getPetType());
			}
		});
		return Response.ok(foundPetType).build();
	}

	/**
	 * This method deletes the pet type at given id
	 * @param petTypeId
	 * @return petTypes
	 */
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Delete Pet at given id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet Type found for the id.")
	})
	@DELETE
	@Path("petTypes/deletePetType")
	public Response deletePetType(@QueryParam("petTypeId") int petTypeId) {
		if (petTypeId <= 0) {
			return Response.status(Status.NOT_FOUND).build();
		}

		petTypes.removeIf(availablePetTypes -> availablePetTypes.getPetTypeId().equals(petTypeId));

		return Response.ok(petTypes).build();
	}

	/**
	 * This method updates the pet type at given id
	 * @param petTypeId
	 * @param petType
	 * @return petTypes
	 */
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Update Pet Type at the given id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet Type found for the id.")})
	@PUT
	@Path("petTypes/updatePetTypeName")
	public Response updatePetTypeName(
			@QueryParam("petTypeId") int petTypeId,
			@QueryParam("petType") String petType
	) {
		if (petTypeId < 0) {
			return Response.status(Status.NOT_FOUND).build();
		}

		petTypes.forEach(petT -> {
			if (petT.getPetTypeId() == petTypeId) {
				petT.setPetType(petType);
			}
		});
		return Response.ok(petTypes).build();
	}
}
