# Scalo Aeroportuale
Progetto per l'esame di Programmazione ad Oggetti - Gruppo 8

#### Autori
- Ciro Cefalo - ciro.cefalo@unina.it
- Federico Failla - f.failla@studenti.unina.it
- Ferdinando De Cardona - f.decardona@studenti.unina.it


Introduzione
----
Il sistema informativo dello scalo aeroportuale è composto da una base di dati relazionale e da un applicativo realizzato in Java.
Quest'ultimo è dotato di una GUI che permette la gestione di uno scalo aeroportuale.

Il sistema è in grado di gestire <b>le tratte</b> che interessano l’aeroporto, con relativa data e orario di partenza e numero di prenotazioni sulla tratta, <b>i gate</b>, e <b>le compagnie aeree</b> che hanno la base nell’aeroporto.
Ad ogni gate è associata una o più <b>code di imbarco</b> (Famiglie, diversamente abili, priority, business class, etc.).
Un determinato gate è associato a diverse tratte nell’arco della giornata, ma mai a più di una contemporaneamente.
Inoltre, ad ogni gate e tratta è associato anche <b>uno slot</b>, inteso come tempo di imbarco stimato ed uno effettivo, per ognuna delle code presenti.

Il sistema permette di effettuare una ricerca dettagliata di tratte, gate e compagnie aeree, considerando anche le tempistiche di imbarco, evidenziando le tratte che sforano il tempo d’imbarco stimato.
Il sistema consente anche di stimare l’utilizzo di ogni gate nell’arco della giornata/settimana/mese, sia in base del tempo stimato di utilizzo, che in base al tempo effettivo.

Inoltre, il sistema tiene traccia anche di un certo pool di clienti business, associati ad una determinata compagnia aerea.
Ogni cliente business è identificato da un codice <b>CentoKilometri</b> unico per la compagnia.

Il sistema permette di verificare quante volte il volo di un cliente business è stato soggetto a ritardo, per offrire un bonus in punti.

Documentazione
----
Per la documentazione completa, si rimanda al seguente link:<br />
https://drive.google.com/file/d/1gPKQiAjSZwMVeh5K2-dWnaK4AuZXZDyO/view
