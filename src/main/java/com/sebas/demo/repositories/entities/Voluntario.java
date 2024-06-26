package com.sebas.demo.repositories.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "voluntarios")
@Schema(name = "Voluntario", description = "Representa al voluntario en el modelo de base datos")
public class Voluntario implements Serializable{

    @Schema(name = "id", required = true, example = "1", defaultValue = "1", description = "Representa el indicador del voluntario") 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(name = "Nombre_Voluntario", required = true, example = "Luis", defaultValue = "Pedro", description = "Representa el nombre del voluntario") 
    @JsonIgnoreProperties(value={"voluntarios", "hibernateLazyInitializer", "handler"}, allowSetters=true)
    @JoinColumn(name = "id_persona")
    @OneToOne(fetch = FetchType.LAZY)
    private Persona persona;

    @Schema(name = "Nombre_Sede", required = true, example = "Refugio de Inmigrantes", defaultValue = "Refugio de Inmigrantes", description = "Representa la sede a la que esta asignado el voluntario") 
    @JsonIgnoreProperties(value={"voluntarios", "hibernateLazyInitializer", "handler"}, allowSetters=true)
    @ManyToOne()
    @JoinColumn(name = "id_sede")
    private Sede sedes;

    @JsonIgnore
    @OneToOne(mappedBy = "voluntario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private VoluntarioH voluntario;
    
}
