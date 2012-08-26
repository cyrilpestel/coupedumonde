
Accesseur : 
 - {concours}.{manche}.{variable}

Operation :
 + - / *

Manche 
 - expression : l1.2.pts + eu.1.pts
 - numero
 
Eliminatoire
Poule + Eliminatoire



Classement Participant (liste des participants)
Classement Participant Riskyfoot (liste des participants classé par le classement Riskyfoot de l'année 2011/2012)
Classement Participant Riskyfoot Fifa (liste des participants par rapport au classement Fifa)



Algorithme 1 :
------------

IP : Index du participant dans la liste des participants
PX : Participant X
IR : Index du participant dans le classement Riskyfoot de l'année 2011/2012 (/!\ : Il peut y avoir des égalités)
IF : Index equipe Fifa

Pour chaque participant de la liste des participants :
  IP * IR : Index Participant Riskyfoot (IPR)

Pour chaque participant de la liste des Participants riskyfoot :
  IPR * IF : Index Participant Riskyfoot Fifa (IPRF)

Tri de la liste des Participants Fifa

Classes : 
---------

Participant (Pseudo, Equipe Fifa)


(N- PX)
1- P1
2- P2
3- P3
...


Algorithme 2 :
--------------

Récupère le classement de chaque participant.
On tri les participants par ordre de classement.
On attribut à chaque participant une equipe fifa par rapport à leur classement

Poule 