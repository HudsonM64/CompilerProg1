package Parse;
import java.io.PrintWriter;

public class Main {

//test
  public static void main(String argv[]) throws java.io.IOException {
    for (int i = 0; i < argv.length; ++i) {
      String filename = argv[i];
      if (argv.length > 1)
	System.out.println("***Processing: " + filename);
      ErrorMsg.ErrorMsg errorMsg = new ErrorMsg.ErrorMsg(filename);
      java.io.InputStream inp=new java.io.FileInputStream(filename);
      Lexer lexer = new Yylex(inp,errorMsg);
      java_cup.runtime.Symbol tok;

      do {
	 String extra = "";
         tok=lexer.nextToken();
	 switch (tok.sym) {
	 case sym.ID:     extra = "\t$" + tok.value; break;
	 case sym.INT:    extra = "\t#" + tok.value; break;
	 case sym.STRING: extra = " \"" + tok.value + "\""; break;
	 }
	 System.out.println(symnames[tok.sym] + " " + tok.left + extra);
      } while (tok.sym != sym.EOF);

      inp.close();
    }
  }

  static String symnames[] = new String[100];
  static {
     
     symnames[sym.FUNCTION] = "FUNCTION";
     symnames[sym.EOF] = "EOF";
     symnames[sym.INT] = "INT";
     symnames[sym.GT] = "GT";
     symnames[sym.DIVIDE] = "DIVIDE";
     symnames[sym.COLON] = "COLON";
     symnames[sym.ELSE] = "ELSE";
     symnames[sym.OR] = "OR";
     symnames[sym.NIL] = "NIL";
     symnames[sym.DO] = "DO";
     symnames[sym.GE] = "GE";
     symnames[sym.error] = "error";
     symnames[sym.LT] = "LT";
     symnames[sym.OF] = "OF";
     symnames[sym.MINUS] = "MINUS";
     symnames[sym.ARRAY] = "ARRAY";
     symnames[sym.TYPE] = "TYPE";
     symnames[sym.FOR] = "FOR";
     symnames[sym.TO] = "TO";
     symnames[sym.TIMES] = "TIMES";
     symnames[sym.COMMA] = "COMMA";
     symnames[sym.LE] = "LE";
     symnames[sym.IN] = "IN";
     symnames[sym.END] = "END";
     symnames[sym.ASSIGN] = "ASSIGN";
     symnames[sym.STRING] = "STRING";
     symnames[sym.DOT] = "DOT";
     symnames[sym.LPAREN] = "LPAREN";
     symnames[sym.RPAREN] = "RPAREN";
     symnames[sym.IF] = "IF";
     symnames[sym.SEMICOLON] = "SEMICOLON";
     symnames[sym.ID] = "ID";
     symnames[sym.WHILE] = "WHILE";
     symnames[sym.LBRACK] = "LBRACK";
     symnames[sym.RBRACK] = "RBRACK";
     symnames[sym.NEQ] = "NEQ";
     symnames[sym.VAR] = "VAR";
     symnames[sym.BREAK] = "BREAK";
     symnames[sym.AND] = "AND";
     symnames[sym.PLUS] = "PLUS";
     symnames[sym.LBRACE] = "LBRACE";
     symnames[sym.RBRACE] = "RBRACE";
     symnames[sym.LET] = "LET";
     symnames[sym.THEN] = "THEN";
     symnames[sym.EQ] = "EQ";
     symnames[sym.SHORT] = "SHORT";
     symnames[sym.SIGNED] = "SIGNED";
     symnames[sym.ARROW] = "ARROW";
     symnames[sym.DIVASSIGN] = "DIVASSIGN";
     symnames[sym.CONST] = "CONST";
     symnames[sym.PERIOD] = "PERIOD";
     symnames[sym.REGISTER] = "REGISTER";
     symnames[sym.ENUM] = "ENUM";
     symnames[sym.SIZEOF] = "ENUM";
     symnames[sym.INCREMENT] = "INCREMENT";
     symnames[sym.SUBASSIGN] = "SUBASSIGN";
     symnames[sym.AUTO] = "AUTO";
     symnames[sym.TYPEDEF] = "TYPEDEF";
     symnames[sym.LSHIFT] = "LSHIFT";
     symnames[sym.STATIC] = "STATIC";
     symnames[sym.BWISEXORASSIGN] = "BWISEXORASSIGN";
     symnames[sym.RSHIFT] = "RSHIFT";
     symnames[sym.CHAR_LITERAL] = "CHAR_LITERAL";
     symnames[sym.VOLATILE] = "VOLATILE";
     symnames[sym.RSHIFTASSIGN] = "RSHIFTASSIGN";
     symnames[sym.BWISEORASSIGN] = "BWISEORASSIGN";
     symnames[sym.BWISEXOR] = "BWISEXOR";
     symnames[sym.UNSIGNED] = "UNSIGNED";
     symnames[sym.BWISEOR] = "BWISEOR";
     symnames[sym.CONTINUE] = "CONTINUE";
     symnames[sym.DECIMAL_LITERAL] = "DECIMAL_LITERAL";
     symnames[sym.error] = "error";
     symnames[sym.DECREMENT] = "DECREMENT";
     symnames[sym.ELIPSES] = "ELIPSES";
     symnames[sym.MULASSIGN] = "MULASSIGN";
     symnames[sym.STRING_LITERAL] = "STRING_LITERAL";
     symnames[sym.FUN] = "FUN";
     symnames[sym.RETURN] = "RETURN";
     symnames[sym.VOID] = "VOID";
     symnames[sym.ADDASSIGN] = "ADDASSIGN";
     symnames[sym.CHAR] = "CHAR";
     symnames[sym.EXTERN] = "EXTERN";
     symnames[sym.DOUBLE] = "DOUBLE";
     symnames[sym.LONG] = "LONG";
     symnames[sym.SWITCH] = "SWITCH";
     symnames[sym.CASE] = "CASE";
     symnames[sym.UNION] = "UNION";
     symnames[sym.FLOAT] = "FLOAT";
     symnames[sym.DEFAULT] = "DEFAULT";
     symnames[sym.GOTO] = "GOTO";
     symnames[sym.STRUCT] = "STRUCT";
     symnames[sym.POUND] = "POUND";
     symnames[sym.BITWISEAND] = "BITWISEAND";
     symnames[sym.MODULUS] = "MODULUS";
     symnames[sym.MODASSIGN] = "MODASSIGN";
     symnames[sym.BWISEANDASSIGN] = "BWISEANDASSIGN";
     symnames[sym.BWISENOT] = "BWISENOT";
   }

}
