package com.example.Hospital3.Task3;

import static org.junit.jupiter.api.Assertions.*;

import Models.Admission;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class Most_Admissions_Controller_Functional_Test { //Everything internally is mocked so this only tests the endpoint

    //Mock my dependencies for the controller
    @Mock
    private GetAdmissions getAdmissions;

    @Mock
    private GetDayTally getDayTally;

    @Mock
    private HasMultipleBusiestDays hasMultipleBusiestDays;

    @Mock
    private DetermineAllBusyDays determineAllBusyDays;

    @Mock
    private DetermineBusiestDay determineBusiestDay;

    private MockMvc mockMvc;

    @Test
    public void testBusiestDay() throws Exception {
        Admission admissionTest = new Admission();
        admissionTest.setId(1);
        admissionTest.setPatientID(78);
        admissionTest.setAdmissionDate("2023-01-27T17:45:00.000Z"); //short stay ID 78
        admissionTest.setDischargeDate("2023-01-29T17:45:00.000Z");

        Admission admissionTest2 = new Admission();
        admissionTest2.setId(2);
        admissionTest2.setPatientID(79);
        admissionTest2.setAdmissionDate("2023-01-27T17:45:00.000Z");
        admissionTest2.setDischargeDate("2023-12-01T17:45:00.000Z"); //long stay wouldnt expect to see it

        Admission admissionTest3 = new Admission();
        admissionTest3.setId(3);
        admissionTest3.setPatientID(80);
        admissionTest3.setAdmissionDate("2023-11-27T17:45:00.000Z"); //short
        admissionTest3.setDischargeDate("2023-11-28T17:45:00.000Z");

        Admission[] mockAdmissionsArray = new Admission[3];
        mockAdmissionsArray[0] = admissionTest;
        mockAdmissionsArray[1] = admissionTest2;
        mockAdmissionsArray[2] = admissionTest3;


        Map<DayOfWeek, Integer> dayTally = new HashMap<>();
        dayTally.put(DayOfWeek.FRIDAY, 3);

        when(getAdmissions.getAdmissions()).thenReturn(mockAdmissionsArray);
        when(getDayTally.getDayTally(mockAdmissionsArray)).thenReturn(dayTally);
        when(hasMultipleBusiestDays.hasMultipleBusiestDays(dayTally)).thenReturn(false);
        when(determineBusiestDay.determineBusiestDay(dayTally)).thenReturn("Friday");

        mockMvc = standaloneSetup(new Most_Admissions_Controller(getAdmissions, getDayTally, hasMultipleBusiestDays, determineAllBusyDays, determineBusiestDay)).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/busiestday")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Busiest day of the week is Friday"));
    }

}