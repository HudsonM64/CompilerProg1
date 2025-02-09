#include <stdio.h>

int main() {
    // Testing some of the defined tokens
    int x = 10;               // Token: INT, ASSIGN, DECIMAL_LITERAL
    char y = 'a';             // Token: CHAR, CHAR_LITERAL
    double z = 3.14;          // Token: DOUBLE, DECIMAL_LITERAL
    struct Example {          // Token: STRUCT, ID
        int field1;
        char field2;
    } exampleInstance;        // Token: ID, LBRACE, RBRACE

    // Testing a simple function definition
    void myFunction(int a, int b) {  // Token: VOID, ID, LPAREN, INT, RPAREN, LBRACE
        printf("Sum: %d\n", a + b);  // Token: ID, LPAREN, RPAREN
    }

    // Call the function
    myFunction(x, y);          // Token: ID, LPAREN, COMMA, RPAREN

    // Testing a conditional block
    if (x > 5) {               // Token: IF, LPAREN, GT, RPAREN, LBRACE
        printf("Greater than 5\n");  // Token: ID, LPAREN, RPAREN
    } else {                   // Token: ELSE
        printf("Not greater than 5\n");
    }

    // Testing loops
    for (int i = 0; i < 5; i++) { // Token: FOR, LPAREN, ID, ASSIGN, DECIMAL_LITERAL, LT, DECIMAL_LITERAL, RPAREN, LBRACE
        printf("Loop iteration %d\n", i); // Token: ID, LPAREN, RPAREN
    }

    return 0; // Token: RETURN, DECIMAL_LITERAL
}
