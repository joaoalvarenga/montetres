all: Animacao.class Naipe.class Sprite.class Carta.class Troca.class GerenciadorTroca.class Main.class

Animacao.class: src/montetres/troca/Animacao.java
	javac -d . -classpath src/ src/montetres/troca/Animacao.java

Carta.class: src/montetres/sprite/Carta.java
	javac -d . -classpath src/ src/montetres/sprite/Carta.java

GerenciadorTroca.class: src/montetres/troca/GerenciadorTroca.java
	javac -d . -classpath src/ src/montetres/troca/GerenciadorTroca.java

Main.class: src/montetres/Main.java
	javac -d . -classpath src/ src/montetres/Main.java

Naipe.class: src/montetres/Naipe.java
	javac -d . -classpath src/ src/montetres/Naipe.java

Sprite.class: src/montetres/sprite/Sprite.java
	javac -d . -classpath src/ src/montetres/sprite/Sprite.java

Troca.class: src/montetres/troca/Troca.java
	javac -d . -classpath src/ src/montetres/troca/Troca.java

run:
	java -cp . montetres.Main
