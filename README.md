# PriestHelpingTool

EN : The aim of this app is to help an orthodox priest. This may look like a strange project, but I did not have much choice, as it was a project that I needed to make with a colleague for our Java course. My friend and I first started setting up the base classes of our project and then tried to split the work equally. In our first try we split the number of classes, that had to be made, between us, but that did not work out really well and we hadn't decided on a plan yet. My friend, who's github is https://github.com/Pely211, suggested we write our code with the controller-service-repository pattern in mind. After I understood how we would structure our code, we split our workload, this time, successfully. I would write the frontend, and he would write the backend. This proved to work out really well as we talked with one another quite a lot and solved different problems, that concerned the both of us, together, but still held our individuality. 
     
     For this project we used the Eclipse IDE and InteliJ IDE (because of our preferences). For the interface we were limited to Java Swing, as per our task. As our app is in Romanian, I will explain the main functions of our app: 
     First of all, we have a welcome screen with a cross and a button. The button takes the user to the main menu.
     The first button of our app provides the CRUD for the repository that holds the believers.
     The second and the third button provide the CRUD for the repositories that hold all the scheduled religious services which are divided into two categories : those that are scheduled by a christian and those that are scheduled by the priest.
     The fourth button lets the user print the services scheduled for the current day.
     The fifth button provides the CRUD for announcements made by the church and by the priest.
     The sixth button tells the user what saint is remembered today or if there are any special holidays. 
     The seventh button is a help button and the last returns the user to the welcome page.
     We also tried to keep the DRY principle in mind when writing code. My friend also handled validators.
     
     Finally, this project has been a challenge for me as it was the first time I worked with someone else on a major task and it was also the first time I had to keep in mind the app's architecture. There are still things we haven't accounted for, but I think it is enough for now.
     
RO: Scopul aplicației este să ajute un preot ortodox. Am avut de făcut acest proiect cu un coleg de facultate, al cărui github este https://github.com/Pely211. Ne-am chinuit pe la început pentru ca a fost prima oară pentru amândoi când am lucrat cu altcineva la un proiect aproape major, dar în final, după ore de discutat și încercat diferite strategii, colegul meu a sugerat să folosim modelul controller-service-repository. După ce am înțeles principiile din spatele acestui model, am fost de accord și ne-am separat într-un final, cum trebuie, și munca: el s-a ocupat de backend, iar eu de frontend.

    Pentru proiectul acesta am folosit Eclipse IDE și InteliJ IDE (deoarece am avut preferințe diferite). Pentru interfață m-am folosit în principal de Java Swing, deoarece acest lucru era specificat în cerință. Când am scris cod pentru această aplicație am încercat să ținem cont de principiul DRY. Avem și un UML făcut și validatori. Pe pagina principală se află o cruce și un buton care duce utilizatorul la meniul principal. De asemenea, în cadrul aplicației avem implementat CRUD pentru enoriași, servicii religioase, slujbe și predici/anunțuri. Butoanele din această aplicație fac ce sugerează numele. 
    
    În final, proiectul acesta s-a dovedit a fi dificil și mai complex decât aproximativ tot ce am făcut până acum, dar m-am și distrat destul de mult cu el. Mai sunt câteva chestiuni care ar trebui reparate, dar deocamdată este de ajuns și atât. 
