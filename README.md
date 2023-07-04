# Blood-Bank-Spring-Boot-Application

  Această aplicație client-server a fost creată cu scopul de a facilita procesul de donare de sânge, punând în legătură donatorii, doctorii si centrele de donare 
într-un mod eficent și sigur. Prin intermediul acestei aplicații donatorii își pot face programări la centrele de donare din județul lor, își pot vedea istoricul 
donărilor, pot anula programări și își pot vizualiza rezultatele analizelor. Pe de alta parte, aplicația ușurează și munca doctorilor care pot vizualiza programările 
din ziua curentă, le pot confirma și pot trimite rezultatele analizelor prin doar câteva apăsări de butoane. Implementarea acestei aplicații a devenit o necesitate 
având în vedere provocările întâmpinate în procesul tradițional de donare de sânge, care adesea implică programări telefonice și comunicare complexă. 

##	Diagrama de use case

Diagrama de mai jos ilustrează toate cazurile de utilizare ale aplicției, având urmatorii actori:
* Doctorul -  un utilizator care are rolul de a confirma donările și de a trimite rezultatele acestora. Doctorul interacționează cu aplicația pentru a accesa informațiile necesare și a înregistra rezultatele donărilor.
* Donatorul - un utilizator care are rolul de a face programări pentru donări, de a vedea rezultatele donărilor anterioare și de vizualiza situatia centrelor de donare. Donatorul utilizează aplicația pentru a programa donările și a obține informații actualizate despre rezultatele analizelor.
* Adminul - un utilizator cu drepturi administrative, responsabil de gestionarea datelor din sistem. Adminul poate efectua operații CRUD (creare, citire, actualizare, ștergere) pe conturile doctorilor.
<img src="/images/usecase.png" width="450" height="400">


## Tehnologii utilizate

###	Spring Boot 
Am utilizat framework-ul Spring Boot pentru dezvoltarea backend-ului aplicației. Spring Boot oferă un mediu de dezvoltare rapidă și eficientă, cu suport pentru gestionarea dependențelor, configurare automată și integrarea ușoară cu alte tehnologii. 
###	Vue.js
Pentru partea de frontend, am ales să folosesc framework-ul Vue.js datorită abordării sale reactive și flexibile. Vue.js mi-a permis să construiesc interfața utilizator interactivă și dinamică, utilizând componentele Vue.js pentru a gestiona starea aplicației și interacțiunea cu utilizatorul.
###	MySQL 
Pentru stocarea datelor, am folosit baza de date relațională MySQL. Am utilizat Spring Data JPA pentru a interacționa cu baza de date și a realiza operațiuni de citire și scriere a datelor. Prin asta am obținut un nivel ridicat de abstractizare și eficiență în interacțiunea cu baza de date.
###	HTML, CSS și JavaScript
 Aceste tehnologii de bază au fost utilizate pentru construirea interfeței utilizator în partea de frontend. HTML a fost folosit pentru structura și conținutul paginilor web, CSS pentru stilizarea și aspectul acestora, iar JavaScript pentru interactivitate și funcționalități dinamice.
În dezvoltarea aplicației, am avut în vedere și principiile SOLID. Respectarea acestor principii m-a ajutat să obținn un cod modular, extensibil și ușor de întreținut. Am aplicat principii precum Single Responsibility Principle, Open-Closed Principle și Dependency Inversion Principle în structurarea și implementarea componentelor. Astfel, am asigurat o separare clară a responsabilităților și o flexibilitate crescută în dezvoltarea și întreținerea aplicației pentru donarea de sânge.
