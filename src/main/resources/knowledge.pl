% Information taken from Centers for Disease Control and Prevention (CDC)
% at https://www.cdc.gov/cholera

disease(Patient, cholera) :-
    symptom(Patient, dehydration),
    symptom(Patient, rice_water_stools).
%    (
%        symptom(Patient, abdominal_pain);
%        symptom(Patient, rectal_pain);
%        symptom(Patient, fever);
%       symptom(Patient, severe_vomiting);
%       symptom(Patient, seizures)
%  ).

symptom(Patient, dehydration) :-
    symptom(Patient, mild_dehydration);
    symptom(Patient, moderate_dehydration);
    symptom(Patient, severe_dehydration).

symptom(Patient, mild_dehydration) :- symptom(Patient, diarrhea).

symptom(Patient, severe_dehydration) :-
    symptom(Patient, lethargic);
    symptom(Patient, weak_pulse);
    symptom(Patient, respiratory_distress).

symptom(Patient, severe_dehydration) :-
    symptom(Patient, sunken_eyes),
    (
        symptom(Patient, drinks_poorly);
        symptom(Patient, skin_pinch_goes_back_very_slowly)
    ).
symptom(Patient, severe_dehydration) :-
    symptom(Patient, drinks_poorly),
    (
        symptom(Patient, sunken_eyes);
        symptom(Patient, skin_pinch_goes_back_very_slowly)
    ).

symptom(Patient, severe_dehydration) :-
    symptom(Patient, skin_pinch_goes_back_very_slowly),
    (
        symptom(Patient, drinks_poorly);
        symptom(Patient, sunken_eyes)
    ).

symptom(Patient, moderate_dehydration) :-
    symptom(Patient, irritable),
    symptom(Patient, sunken_eyes),
    (
        symptom(Patient, rapid_pulse);
        symptom(Patient, thirsty);
        symptom(Patient, skin_pinch_goes_back_slowly)
    ).

symptom(Patient, moderate_dehydration) :-
    symptom(Patient, irritable),
    symptom(Patient, rapid_pulse),
    (
        symptom(Patient, sunken_eyes);
        symptom(Patient, thirsty);
        symptom(Patient, skin_pinch_goes_back_slowly)
    ).

symptom(Patient, moderate_dehydration) :-
    symptom(Patient, irritable),
    symptom(Patient, thirsty),
    (
        symptom(Patient, sunken_eyes);
        symptom(Patient, rapid_pulse);
        symptom(Patient, skin_pinch_goes_back_slowly)
    ).

symptom(Patient, moderate_dehydration) :-
    symptom(Patient, irritable),
    symptom(Patient, skin_pinch_goes_back_slowly),
    (
        symptom(Patient, sunken_eyes);
        symptom(Patient, rapid_pulse);
        symptom(Patient, thirsty)
    ).

symptom(Patient, moderate_dehydration) :-
    symptom(Patient, sunken_eyes),
    symptom(Patient, rapid_pulse),
    (
        symptom(Patient, irritable);
        symptom(Patient, skin_pinch_goes_back_slowly);
        symptom(Patient, thirsty)
    ).

symptom(Patient, moderate_dehydration) :-
    symptom(Patient, sunken_eyes),
    symptom(Patient, thirsty),
    (
        symptom(Patient, irritable);
        symptom(Patient, skin_pinch_goes_back_slowly);
        symptom(Patient, rapid_pulse)
    ).

symptom(Patient, moderate_dehydration) :-
    symptom(Patient, sunken_eyes),
    symptom(Patient, skin_pinch_goes_back_slowly),
    (
        symptom(Patient, irritable);
        symptom(Patient, thirsty);
        symptom(Patient, rapid_pulse)
    ).

symptom(Patient, moderate_dehydration) :-
    symptom(Patient, rapid_pulse),
    symptom(Patient, thirsty),
    (
        symptom(Patient, irritable);
        symptom(Patient, skin_pinch_goes_back_slowly);
        symptom(Patient, sunken_eyes)
    ).

symptom(Patient, moderate_dehydration) :-
    symptom(Patient, rapid_pulse),
    symptom(Patient, skin_pinch_goes_back_slowly),
    (
        symptom(Patient, irritable);
        symptom(Patient, thirsty);
        symptom(Patient, sunken_eyes)
    ).

symptom(Patient, moderate_dehydration) :-
    symptom(Patient, thirsty),
    symptom(Patient, skin_pinch_goes_back_slowly),
    (
        symptom(Patient, irritable);
        symptom(Patient, rapid_pulse);
        symptom(Patient, sunken_eyes)
    ).

treatment(mild_dehydration, mild_rehydration_therapy).
treatment(mild_dehydration, zinc_supplementation).
treatment(moderate_dehydration, moderate_rehydration_therapy).
treatment(moderate_dehydration, antibiotics_treatment).
treatment(moderate_dehydration, zinc_supplementation).
treatment(severe_dehydration, severe_rehydration_therapy).
treatment(severe_dehydration, antibiotics_treatment).
treatment(severe_dehydration, zinc_supplementation).

dose(Age, mild_rehydration_therapy, '50-100ml of Oral Rehydration Salts after each evacuation, providing a volume similar to the assessed fluid loss') :-
    Age < 2.
dose(Age, mild_rehydration_therapy, '100-200ml of Oral Rehydration Salts up to 1000ml/day') :-
    Age >= 2, Age =< 14.
dose(Age, mild_rehydration_therapy, 'Drink the amount of Oral Rehydration Salts needed, ingesting a volume similar to the assessed fluid loss, up to two liters daily') :-
    Age >= 14.

dose(Age, moderate_mild_rehydration_therapy,
'Administer within first 4 hours: Children less than 4 months(less than 5kg) 200-400 ml. Children from 4 to 11 months (5 to 7.9 kg) 400-600 ml') :-
    Age < 1.

dose(Age, moderate_mild_rehydration_therapy,'Administer within first 4 hours: Children (8 to 10.9 kg) 600-800ml') :-
    Age >= 1, Age =< 2.

dose(Age, moderate_mild_rehydration_therapy,
'Administer within first 4 hours: Children (11 to 15.9 kg) 800-1200ml') :-
    Age > 2, Age =< 4.

dose(Age, moderate_mild_rehydration_therapy,
'Administer within first 4 hours: Children (16 to 29.9 kg) 1200-2200ml') :-
    Age > 4, Age =< 14.

dose(Age, moderate_mild_rehydration_therapy,
'Administer within first 4 hours: Children (30 kg or more) 2200-4000ml') :-
    Age > 14.

dose(_, severe_rehydration_therapy, 'Intravenous lactated Ringers solution is recommended at the following perfusion rate: 1st hour: 50ml/kg, 2nd hour: 25 ml/kg, 3rd hour: 25 ml/kg. Clinical assessment should be use to determine whether to continue intravenous rehydration. Closely monitor radial pulse or capillary nail refill time to assess the dehydration. If the pulse is weak or the capillary perfusion is greater than 2 seconds, increase the speed of perfusion. Start oral rehydration as soon as the patient is able to drink. Follow the guideline for moderate dehydration, always adapting to the volume of fluid loss').

dose(Age, antibiotics_treatment, 'First-line drug choice: Doxycycline  2-4 mg/kg by mouth (per os, p.o.) single dose. Alternate drug choices: Azithromycin 20 mg/kg (max 1 g) p.o. single dose, or ciprofloxacin 20 mg/kg (max 1 g) p.o. single dose ') :-
    Age < 12.

dose(Age, antibiotics_treatment, 'First-line drug choice: Doxycycline 300 mg p.o. single dose. Alternate drug choices: Azithromycin 1 g p.o. single dose, or ciprofloxacin 1 g p.o. single dose') :-
    Age >= 12.

dose(_, zinc_supplementation, '20 mg zinc_supplementation per day').

% End of knowledge file