//package com.mmt.diagnosis.domain;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Entity
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//public class Authorities {
//
//    @JsonIgnore
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long authorityId;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id")
//    private Users user;
//
//    @ManyToOne
//    @JoinColumn(name = "role_name")
//    private Roles role;
//
//}
