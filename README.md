# AirCondition
Project made for Objective Programming course. <br />
Program is using [Jakość Powietrza](http://powietrze.gios.gov.pl/pjp/content/api) API to get data from stations that measure air condition in Poland. It downloades data that is necessary to execute selected operations. It also saves received JSONs in text files (updated when data is no longer actual) to make operations much more faster.

## Examples
### PM10 content in *Kraków, aleja Krasińskiego* from 2019-03-26 10:00 to 2019-03-26 20:00
    2019-03-26 10:00:00 Kraków, Aleja Krasińskiego              |||||||||||||||||||||||||||||||||||||||||||||||||| 38.915
    2019-03-26 11:00:00 Kraków, Aleja Krasińskiego              |||||||||||||||||||||||||||||||||||| 28.5441
    2019-03-26 12:00:00 Kraków, Aleja Krasińskiego              ||||||||||||||||||||||||||||||||||||||||||| 34.0757
    2019-03-26 13:00:00 Kraków, Aleja Krasińskiego              ||||||||||||||||||||||||||||||||||||||| 30.6774
    2019-03-26 14:00:00 Kraków, Aleja Krasińskiego              |||||||||||||||||||||||||||||||| 25.3164
    2019-03-26 15:00:00 Kraków, Aleja Krasińskiego              ||||||||||||||||||||||||||||| 23.0759
    2019-03-26 16:00:00 Kraków, Aleja Krasińskiego              ||||||||||||||||||||||||| 20.061
    2019-03-26 17:00:00 Kraków, Aleja Krasińskiego              |||||||||||||||||||||||||||||||||||| 28.3909
    2019-03-26 18:00:00 Kraków, Aleja Krasińskiego              ||||||||||||||||||||||||||||| 23.329
    2019-03-26 19:00:00 Kraków, Aleja Krasińskiego              |||||||||||||||||||||||||||||||||||| 28.6292
    2019-03-26 20:00:00 Kraków, Aleja Krasińskiego              |||||||||||||||||||||||||||||||||| 26.8687

### Maximum and minimum (greater than zero) content of selected parameter in whole Poland (concerns measures that are currently available, i.e. from last few days)
    PM10
    Wartość największa (maximum value):
    Nowiny, ul. Parkowa 2019-03-26 19:00:00 345.61
    Wartość najmniejsza (minimum value):
    Olesno automat 4 2019-03-25 17:00:00 0.4
    
### Average value of selected parameter, station and time period
    Wartość średnia parametru NO2 dla stacji Wrocław - Bartnicza wynosi: 9.95
