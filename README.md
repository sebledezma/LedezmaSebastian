# LedezmaSebastian
TP Final noté de Java

sujet : Course de serveurs ------------------

Faire trois serveurs quasiment identiques : deux calculent la fonction
factorielle en itératif avec un while ou avec un for, le troisième
calcule factorielle en récursif (voir la définition de factorielle
ci-dessous).

Faire un client qui effectue 5000 demandes de factorielle, pour une
valeur constante, en utilisant un des trois serveurs de façon aléatoire.
Les paquets transmis par le client contiennent la date d'envoi (new
GregorianCalendar().getTimeInMillis()), ceux transmis par le serveur
contiennent la date d'envoi envoyée par le client et la date courante du
serveur. Tout cela est synchrone.

Après chaque paquet reçu, le client envoie, en mode asynchrone, le
couple (temps de calcul, serveur) à un 4ème serveur qui centralise les
résultats obtenus et les compare au fur et à mesure.

Pour rappel, en récursif factorielle (n) = n x factorielle(n-1), avec
factorielle(0) = factorielle(1) = 1

exemple factorielle(6) = 6! = 6x5x4x3x2x1=720
