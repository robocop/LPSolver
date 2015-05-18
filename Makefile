all:
	mkdir -p bin
<<<<<<< HEAD
=======
	chmod +x toto
	chmod +x totoopt
	chmod +x totoprint
>>>>>>> review2
	javac -cp .:antlr-4.5-complete.jar  -d bin src/fr/enslyon/Main.java -sourcepath src

