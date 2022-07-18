# SurvivorCamp

Survivor Camp - Apocalypse: Implementation(Spring boot)
This app provides the backend for storing survivors information and updating 

## Documentation
Swagger {{url}}/swagger-ui/index.html

## Postman request for APIs

getSurvivors {{url}}/getSurvivors
    Returns the List of all survivors in the camp

addSurvivor  {{url}}/registerSurvivors?firstUser=user1&secondUser=user2
    stores the survivor details

reportInfected  {{url}}/reportInfected?reporterId=4&infectedId=5
    stores the contamination of a survivor reported by the user and updates the infected flag if 3 or more users report the contamination

getReportDetails {{url}}/getReports
    Returns the detailed report of the survivors listed in the camp

updateSurvivorLocation {{url}}/updateSurvivorLocation/1?longitude=33.25&latitude=11.25
    Updates the location of the survivor