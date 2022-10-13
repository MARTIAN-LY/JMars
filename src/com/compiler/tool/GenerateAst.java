package com.compiler.tool;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * Script to generate classes.
 **/

public class GenerateAst {
    public static void main(String[] args) throws IOException {
//        if (args.length != 1) {
//            System.err.println("Usage: generate_ast <output directory>");
//            System.exit(64);
//        }
        String outputDir = "src/com/compiler/mars";

        List<String> types = Arrays.asList(
                "Binary     : Expr left,Token operator,Expr right",
                "Grouping   : Expr expression",
                "Literal    : Object value",
                "Unary      : Token operator,Expr right"
        );

        // Generate classes.
        defineAst(outputDir, "Expr", types);


    }

    private static void defineAst(String outputDir, String basename, List<String> types) throws IOException {

        // Generate some basic imports.
        String path = outputDir + "/" + basename + ".java";
        PrintWriter writer = new PrintWriter(path, StandardCharsets.UTF_8);
        writer.println("package com.compiler.mars;");
        writer.println();
        writer.println("import java.util.List;");
        writer.println();
        writer.println("abstract class " + basename + " {");

        // Generate fields and methods.
        for (String type : types) {
            String[] split = type.split(":");
            String classname = split[0].trim();
            String fields = split[1].trim();

            defineType(writer, basename, classname, fields);

        }
        writer.println("}");

        //  Generate visitors.
//        defineVisitor(writer, basename, types);

        writer.close();
    }

    private static void defineType(PrintWriter writer, String basename, String classname, String fieldList) {

        writer.println("\tstatic class " + classname + " extends " + basename + "{");

        String[] fields = fieldList.split(",");

        //fields
        for (String field : fields) {
            writer.println("\t\tfinal " + field + ";");
        }

        // Constructor
        writer.println("\t\t" + classname + "(" + fieldList + ") {");

        for (String field : fields) {
            String name = field.split(" ")[1];
            writer.println("\t\t\tthis." + name + " = " + name + ";");
        }
        writer.println("\t\t}");


        writer.println("\t}");
    }

//    private static void defineVisitor(PrintWriter writer, String basename, List<String> types) {
//        writer.println("interface Visitor<T> {");
//
//        for (String type : types) {
//
//        }
//    }
}
