package com.project.test.parameta.empleadorest.dto;

import com.project.test.parameta.commons.util.anotations.CargoValidartionAnnotation;
import com.project.test.parameta.commons.util.anotations.FechaValidationAnotation;
import com.project.test.parameta.commons.util.anotations.TipoDocumentoValidationAnnotation;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class EmpleadoRequestDTO {

    @NotNull
    @NotBlank
    private String nombres;

    @NotNull
    @NotBlank
    private String apellidos;

    @NotNull
    @NotBlank
    @TipoDocumentoValidationAnnotation
    private String tipoDocumento;

    @NotNull
    @NotBlank
    private String numeroDocumento;

    @NotNull
    @NotBlank
    @FechaValidationAnotation
    private String fechaNacimiento;

    @NotNull
    @NotBlank
    @FechaValidationAnotation
    private String fechaVinculacionComania;

    @NotNull
    @NotBlank
    @CargoValidartionAnnotation
    private String cargo;

    @NotNull
    @Min(value = 1)
    @Positive
    private Double salario;

}
