package org.springframework.samples.petclinic.owner;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
public class PetAttributeController {

	@Autowired
	private OwnerRepository ownerRepo;

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@ModelAttribute("petAttribute")
	public PetAttributes loadPetAttribute(@PathVariable("ownerId") int ownerId, @PathVariable("petId") int petId,
			Map<String, Object> model) {
		Optional<Owner> optionalOwner = ownerRepo.findById(ownerId);
		Owner owner = optionalOwner.orElseThrow(() -> new IllegalArgumentException(
				"Owner not found with id: " + ownerId + ". Please ensure the ID is correct "));

		Pet pet = owner.getPet(petId);
		model.put("pet", pet);
		model.put("owner", owner);

		PetAttributes petAttributes = new PetAttributes();
		pet.setPetAttribute(petAttributes);
		return petAttributes;
	}

	@GetMapping("/owners/{ownerId}/pets/{petId}/petAttribute/new")
	public String initNewPetAttributeForm() {
		return "pets/createOrUpdatePetAttributeForm";
	}

	@PostMapping("/owners/{ownerId}/pets/{petId}/PetAttribute/new")
	public String processNewVisitForm(@ModelAttribute Owner owner, @PathVariable int petId,
			@Valid PetAttributes petAttribute, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "pets/createOrUpdatePetAttributeForm";
		}

		owner.addPetAttribute(petId, petAttribute);
		ownerRepo.save(owner);
		redirectAttributes.addFlashAttribute("message", "Your visit has been booked");
		return "redirect:/owners/{ownerId}";
	}

}
