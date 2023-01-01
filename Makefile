SOURCES = $(shell find src -type f -name "*.java")
CLASSES = $(patsubst src/%.java,target/%.class,$(SOURCES))
ANTLR_SOURCES = $(shell find src -type f -name "*.g4")
ANTLR_RESULTS = src/$(PACKAGE)/parsing/antlr/generated
ANTLR = lib/antlr-4.11.1-complete.jar
MAINCLASS = ru.vityaman.itmo.math.logic.expression.Main
PACKAGE = ru/vityaman/itmo/math/logic/expression
DIST=dist/expression_parser.zip

all: $(ANTLR_SOURCES) $(ANTLR_RESULTS)/ExpressionParser.java $(CLASSES)

$(ANTLR_RESULTS)/ExpressionParser.java: src/$(PACKAGE)/parsing/antlr/Expression.g4
	java -jar ${ANTLR} $< -o $(ANTLR_RESULTS) -Xexact-output-dir

target/%.class: src/%.java target
	javac -cp src:${ANTLR} $< -d target

target:
	mkdir -p target

dist:
	mkdir -p dist

clean:
	- rm -rf target 
	- rm -f src/parsing/antlr/generated/*.java
	- rm -rf dist

run: 
	java -cp target:${ANTLR} ${MAINCLASS}

clean-build: clean all

release: clean-build dist
	zip $(DIST) -r Makefile src lib
