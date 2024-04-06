package ma.emsi.hospital.web;

import ma.emsi.hospital.entity.Patient;
import ma.emsi.hospital.repo.PatientRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepo patientRepo;

    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "0") int page,
                        @RequestParam(name = "size",defaultValue = "4") int size){
        Page<Patient> patientList=patientRepo.findAll(PageRequest.of(page,size));
        model.addAttribute("listPatient",patientList.getContent());
        model.addAttribute("pages",new int[patientList.getTotalPages()]);
        model.addAttribute("current_page",page);
        return "patients";
    }
}
