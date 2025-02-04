package Parse;
import ErrorMsg.ErrorMsg;

%% 

%implements Lexer
%function nextToken
%type java_cup.runtime.Symbol
%char

%{
private void newline() {
  errorMsg.newline(yychar);
}

private void err(int pos, String s) {
  errorMsg.error(pos,s);
}

private void err(String s) {
  err(yychar,s);
}

private java_cup.runtime.Symbol tok(int kind) {
    return tok(kind, null);
}

private java_cup.runtime.Symbol tok(int kind, Object value) {
    return new java_cup.runtime.Symbol(kind, yychar, yychar+yylength(), value);
}

private ErrorMsg errorMsg;

Yylex(java.io.InputStream s, ErrorMsg e) {
  this(s);
  errorMsg=e;
}

%}

%eofval{
	{
	 return tok(sym.EOF, null);
        }
%eofval}       


%%
" "	{}
\n	{newline();}
","	{return tok(sym.COMMA, null);}
int { return tok(sym.INT, null); }
char { return tok(sym.CHAR, null); }
var { return tok(sym.VAR, null); }
fun { return tok(sym.FUN, null); }
[a-zA-Z_][a-zA-Z0-9_]* {return tok(sym.ID, yytext()); }
"=" { return tok(sym.ASSIGN, null); }
"==" { return tok(sym.EQ, null); }
"+" { return tok(sym.PLUS, null); }
"-" { return tok(syn.MINUS, null); }
"*" { return tok(sym.TIMES, null); }
"/" { return tok(sym.DIVIDE, null); }
"&&" { return tok(sym.AND, null); }
"||" { return tok(sym.OR, null); }
"(" { return tok(sym.LPAREN, null); }
")" { return tok(sym.RPAREN, null); }
[0-9]+ { return tok(sym.INT_LITERAL, Integer.parseInt(yytext())); }
'([^'\\]|\\[ntbrf'\\])' { return tok(sym.CHAR_LETERAL, yytext()); }
\"([^\"\\]|\\[ntbrf\"\\])*\" { return tok(sym.STRING_LITERAL, yytext()); }
"//".* { /* Skip single-line comments */ }
"/*"([^*]|"*"[^/])*"*/" { /* Skip multi-line comments */ }
[ \t\r]+ { /* Skip whitespaces */ }
\n { newline(); }

. { err("Illegal character: " + yytext()); }
