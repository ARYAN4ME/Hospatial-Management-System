package com.example.hospital.management;

import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    HashMap<Integer,Doctor> doctorDb = new HashMap<>();

    @PostMapping("/add")
    public String addDoctor(@RequestBody Doctor doctor){

        int doctorId = doctor.getDoctorId();
        doctorDb.put(doctorId,doctor);

        return "doctor added successfully";
    }
    @PostMapping("/addViaParameter")
    public String addDocter(@RequestParam("docterId")int docterId,@RequestParam("name")String name,
                            @RequestParam("specialization")String specialization,@RequestParam("age")int age,
                            @RequestParam("degree")String degree){
        Doctor doctor = new Doctor(docterId,name,specialization,age,degree);
        doctorDb.put(docterId,doctor);
        return "Doctor added successfully by RequestPrem";

    }
    @PostMapping("/addViaRequestBody")
    public String addDocterByBody(@RequestBody Doctor doctor){
        int key = doctor.getDoctorId();
        doctorDb.put(key,doctor);
        return "Doctor add sussessfully by RequestBody";
    }
    @GetMapping("/getInfoViaPathVariable/{doctorId}")
    public Doctor getinfoViaPathVariable(@PathVariable("doctorId")Integer doctorId){
        Doctor doctor = new Doctor();
        if(doctorDb.containsKey(doctorId)){
            doctor = doctorDb.get(doctorId);
        }
        return doctor;
    }
    @GetMapping("/getDoctor/{age}/{degree}")
    public List<Doctor> getDoctor(@PathVariable("age")int age,@PathVariable("degree")String degree){
        List<Doctor> doctors = new ArrayList<>();
        for(Doctor d : doctorDb.values()){
            if(d.getAge()>age && d.getDegree().equals(degree)){
                doctors.add(d);
            }
        }
        return doctors;
    }
    @GetMapping("/getRequstPrem")
    public Doctor getinfoViaRequstPrem(@RequestParam("doctorId")int doctorId){
        Doctor doctor =  doctorDb.get(doctorId);
        return doctor;

    }
    @GetMapping("/getAllDoctors/{doctorId}/{name}")
    public List<Doctor> getAllDoctors(@RequestBody Doctor doctor){
        int key = doctor.getDoctorId();
        List<Doctor> doctors = new ArrayList<>();
        if(doctorDb.containsKey(key)){
            for(Doctor d : doctorDb.values())
                doctors.add(d);
        }
        return doctors;
    }
    @GetMapping("/getDoctor")
    public List<Doctor> getDoctor(){
        List<Doctor> doctors = new ArrayList<>();
        for(Doctor d : doctorDb.values()){
            doctors.add(d);
        }

        return doctors;
    }
    @GetMapping("/getEndPoint")
    public Doctor getByName(@RequestParam("name")String name){
        Doctor doctor = new Doctor();
        for(Doctor doctors : doctorDb.values()) {
            if (doctor.getName().equals(name)) {
                return doctors;
            }
        }
        return null;
    }
    static int count = 0;
    @GetMapping("/getByAge")
    public int getInfoByAge(@RequestParam("age")Integer age){
//        List<Doctor> doctors = new ArrayList<>();

        for(Doctor d : doctorDb.values()){
            if(d.getAge()>age){
                count++;
//                doctors.add(d);
            }
        }
        return count;
    }
    @PutMapping("/UpdateSpecialization")
    public String updateSpecialization(@RequestParam("doctorId")Integer doctorId,@RequestParam("specialization")String specialization){
        if(doctorDb.containsKey(doctorId)){
            Doctor doctor = doctorDb.get(doctorId);
            doctor.setSpecialization(specialization);
            doctorDb.put(doctorId,doctor);
            return "Doctor specialization Update successfully";
        }
        else {
            return "enter velid doctor Id";
        }
    }
    @DeleteMapping("/Deletedata")
    public String DeleteDoctor(@RequestParam("doctorId")Integer doctorId){
        if(doctorDb.containsKey(doctorId)){
            doctorDb.remove(doctorId);
            return "Doctor delete successfully";
        }
        else{
            return "Enter velid Doctor Id";
        }
    }
    @GetMapping("/howMannyDoctorInYourDB")
    public int doctorSize(){
        return doctorDb.size();
    }

}
