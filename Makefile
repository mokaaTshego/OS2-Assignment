JAVAC=/usr/bin/javac
.SUFFIXES: .java .class

SRCDIR=src
BINDIR=bin
DOCDIR=doc

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<

CLASSES=BarrierReusable.class Propane.class Carbon.class Hydrogen.class RunSimulation.class 

CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

clean:
	rm bin/molecule/*.class
run:
	java -cp bin molecule.RunSimulation 24 9

run1:
	java -cp bin molecule.RunSimulation 4 12

run2:
	java -cp bin molecule.RunSimulation 8 2



