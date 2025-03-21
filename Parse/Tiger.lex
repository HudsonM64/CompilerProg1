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

%{
   StringBuffer string = new StringBuffer();
   private int commentcount = 0;
%}

%eofval{
  {
	  return tok(sym.EOF, null);
  }
%eofval}

%%
" "	{}
\n	{newline();}

int {return tok(sym.INT, null);}
char {return tok(sym.CHAR, null);}
var {return tok(sym.VAR, null);}
fun {return tok(sym.FUN, null);}
auto {return tok(sym.AUTO, null);}
double {return tok(sym.DOUBLE, null);}
struct {return tok(sym.STRUCT, null);}
break {return tok(sym.BREAK, null);}
else {return tok(sym.ELSE, null);}
long {return tok(sym.LONG, null);}
switch {return tok(sym.SWITCH, null);}
case {return tok(sym.CASE, null);}
enum {return tok(sym.ENUM, null);}
register {return tok(sym.REGISTER, null);}
typedef {return tok(sym.TYPEDEF, null);}
extern {return tok(sym.EXTERN, null);}
return {return tok(sym.RETURN, null);}
union {return tok(sym.UNION, null);}
const {return tok(sym.CONST, null);}
float {return tok(sym.FLOAT, null);}
short {return tok(sym.SHORT, null);}
unsigned {return tok(sym.UNSIGNED, null);}
continue {return tok(sym.CONTINUE, null);}
for {return tok(sym.FOR, null);}
signed {return tok(sym.SIGNED, null);}
void {return tok(sym.VOID, null);}
default {return tok(sym.DEFAULT, null);}
goto {return tok(sym.GOTO, null);}
sizeof {return tok(sym.SIZEOF, null);}
volatile {return tok(sym.VOLATILE, null);}
do {return tok(sym.DO, null);}
if {return tok(sym.IF, null);}
while {return tok(sym.WHILE, null);}
static {return tok(sym.STATIC, null);}




[0-9]+ {return tok(sym.DECIMAL_LITERAL, yytext());}

[a-zA-Z_][a-zA-Z0-9_]* { return tok(sym.ID, yytext()); }

"(" {return tok(sym.LPAREN, null);}
")" {return tok(sym.RPAREN, null);}
"[" {return tok(sym.LBRACK, null);}
"]" {return tok(sym.RBRACK, null);}
"{" {return tok(sym.LBRACE, null);}
"}" {return tok(sym.RBRACE, null);}
"." {return tok(sym.PERIOD, null);}
","	{return tok(sym.COMMA, null);}
":" {return tok(sym.COLON, null);}
";" {return tok(sym.SEMICOLON, null);}
"+" {return tok(sym.PLUS, null);}
"-" {return tok(sym.MINUS, null);}
"*" {return tok(sym.TIMES, null);}
"/" {return tok(sym.DIVIDE, null);}
"=" {return tok(sym.EQ, null);}
"==" {return tok(sym.EQ, null);}
"<=" {return tok(sym.LE, null);}
">=" {return tok(sym.GE, null);}
"<" {return tok(sym.LT, null);}
">" {return tok(sym.GT, null);}
"<>" {return tok(sym.NEQ, null);}
"&&" {return tok(sym.AND, null);}
"||" {return tok(sym.OR, null);}
":=" {return tok(sym.ASSIGN, null);}
"..." {return tok(sym.ELIPSES, null);}
"#" {return tok(sym.POUND, null);}
"++" {return tok(sym.INCREMENT, null);}
"--" {return tok(sym.DECREMENT, null);}
"&" {return tok(sym.BITWISEAND, null);}
"~" {return tok(sym.TILDE, null);}
"sizeof" {return tok(sym.SIZEOF, null);}
"%" {return tok(sym.MODULUS, null);}
"<<" {return tok(sym.LSHIFT, null);}
">>" {return tok(sym.RSHIFT, null);}
"!=" {return tok(sym.NEQ, null);}
"^" {return tok(sym.BWISEXOR, null);}
"|" {return tok(sym.BWISEOR, null);}
"*=" {return tok(sym.MULASSIGN, null);}
"/=" {return tok(sym.DIVASSIGN, null);}
"%=" {return tok(sym.MODASSIGN, null);}
"+=" {return tok(sym.ADDASSIGN, null);}
"-=" {return tok(sym.SUBASSIGN, null);}
"<<=" {return tok(sym.LSHIFTASSIGN, null);}
">>=" {return tok(sym.RSHIFTASSIGN, null);}
"&=" {return tok(sym.BWISEANDASSIGN, null);}
"^=" {return tok(sym.BWISEXORASSIGN, null);}
"|=" {return tok(sym.BWISEORASSIGN, null);}
"->" {return tok(sym.ARROW, null);}
"?" {return tok(sym.COPERATOR, null);}
":" {return tok(sym.COLON, null);}
"##" {return tok(sym.TOKENOPERATOR, null);}

. { err("Illegal character: " + yytext()); }