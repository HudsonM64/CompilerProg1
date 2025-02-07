package Parse;
import ErrorMsg.ErrorMsg;


class Yylex implements Lexer {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final char YYEOF = '\uFFFF';

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
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private char yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YYEOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YYEOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_start () {
		++yychar;
		++yy_buffer_start;
	}
	private void yy_pushback () {
		--yy_buffer_end;
	}
	private void yy_mark_start () {
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
private int [][] unpackFromString(int size1, int size2, String st)
    {
      int colonIndex = -1;
      String lengthString;
      int sequenceLength = 0;
      int sequenceInteger = 0;
      int commaIndex;
      String workString;
      int res[][] = new int[size1][size2];
      for (int i= 0; i < size1; i++)
	for (int j= 0; j < size2; j++)
	  {
	    if (sequenceLength == 0) 
	      {	
		commaIndex = st.indexOf(',');
		if (commaIndex == -1)
		  workString = st;
		else
		  workString = st.substring(0, commaIndex);
		st = st.substring(commaIndex+1);
		colonIndex = workString.indexOf(':');
		if (colonIndex == -1)
		  {
		    res[i][j] = Integer.parseInt(workString);
		  }
		else 
		  {
		    lengthString = workString.substring(colonIndex+1);  
		    sequenceLength = Integer.parseInt(lengthString);
		    workString = workString.substring(0,colonIndex);
		    sequenceInteger = Integer.parseInt(workString);
		    res[i][j] = sequenceInteger;
		    sequenceLength--;
		  }
	      }
	    else 
	      {
		res[i][j] = sequenceInteger;
		sequenceLength--;
	      }
	  }
      return res;
    }
	private int yy_acpt[] = {
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR
	};
	private int yy_cmap[] = {
		0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 1, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0,
		2, 3, 0, 4, 0, 5, 6, 0,
		7, 8, 9, 10, 11, 12, 13, 14,
		15, 15, 15, 15, 15, 15, 15, 15,
		15, 15, 16, 17, 18, 19, 20, 0,
		0, 21, 21, 21, 21, 21, 21, 21,
		21, 21, 21, 21, 21, 21, 21, 21,
		21, 21, 21, 21, 21, 21, 21, 21,
		21, 21, 21, 22, 0, 23, 24, 21,
		0, 25, 26, 27, 28, 29, 30, 31,
		32, 33, 21, 34, 35, 36, 37, 38,
		39, 21, 40, 41, 42, 43, 44, 45,
		46, 47, 48, 49, 50, 51, 52, 0
		
	};
	private int yy_rmap[] = {
		0, 1, 1, 1, 1, 2, 3, 1,
		1, 4, 5, 1, 6, 7, 8, 9,
		10, 1, 11, 12, 13, 14, 1, 1,
		15, 1, 16, 1, 1, 1, 1, 1,
		1, 1, 1, 1, 1, 1, 1, 1,
		17, 1, 1, 1, 1, 18, 1, 19,
		14, 1, 1, 1, 1, 1, 14, 14,
		14, 14, 14, 14, 14, 14, 14, 14,
		14, 14, 14, 14, 14, 14, 14, 14,
		14, 14, 14, 14, 14, 14, 14, 14,
		14, 14, 14, 14, 14, 20, 21, 22,
		23, 24, 25, 26, 27, 28, 29, 30,
		31, 32, 33, 34, 35, 36, 37, 38,
		39, 40, 41, 42, 43, 44, 45, 46,
		47, 48, 49, 50, 51, 52, 53, 54,
		55, 56, 57, 58, 59, 60, 61, 62,
		63, 64, 65, 66, 67, 68, 69, 70,
		71, 72, 73, 74, 75, 76, 77, 78,
		79, 80, 81, 82, 83, 84, 85, 86,
		87, 88, 89, 90, 91, 92, 93, 94,
		95, 96, 97, 98, 99, 100, 101, 102,
		103, 104, 105, 106, 107, 108, 109, 110,
		111, 112, 113, 114, 115, 116, 117, 118,
		119, 120, 121, 122, 123, 124, 125, 126,
		127, 128, 129 
	};
	private int yy_nxt[][] = unpackFromString(130,53,
"1,2,3,86,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,149,173,150,87,151,120,152,21,88,21,153,21:4,188,174,194,175,121,176,21:3,25,26,27,28,-1:72,30,-1:39,31,-1:12,32,-1:52,33,-1:43,34,-1:8,35,-1:45,36,-1:6,37,-1:46,85,-1:58,38,-1:48,15,-1:56,39,-1:51,40,41,42,-1:51,43,-1:52,44,45,-1:47,21,-1:5,21,-1:3,21:24,-1:23,46,-1:52,49,-1:30,50,-1:21,52,-1:52,53,-1:48,21,-1:5,21,-1:3,21:18,160,21:5,-1:17,51,-1:58,29,-1:48,21,-1:5,21,-1:3,21:4,189,21:8,47,21:10,-1:19,21,-1:5,21,-1:3,21:5,48,21:6,91,21:11,-1:19,21,-1:5,21,-1:3,21:15,54,21:8,-1:19,21,-1:5,21,-1:3,21:12,55,21:11,-1:19,21,-1:5,21,-1:3,21:17,56,21:6,-1:19,21,-1:5,21,-1:3,21:15,57,21:8,-1:19,21,-1:5,21,-1:3,21:13,58,21:10,-1:19,21,-1:5,21,-1:3,21:4,59,21:19,-1:19,21,-1:5,21,-1:3,21:15,60,21:8,-1:19,21,-1:5,21,-1:3,21:4,61,21:19,-1:19,21,-1:5,21,-1:3,21:11,62,21:12,-1:19,21,-1:5,21,-1:3,21:13,63,21:10,-1:19,21,-1:5,21,-1:3,21:6,64,21:17,-1:19,21,-1:5,21,-1:3,21:3,65,21:20,-1:19,21,-1:5,21,-1:3,21:9,66,21:14,-1:19,21,-1:5,21,-1:3,21:17,67,21:6,-1:19,21,-1:5,21,-1:3,21:17,68,21:6,-1:19,21,-1:5,21,-1:3,21:17,69,21:6,-1:19,21,-1:5,21,-1:3,21:12,70,21:11,-1:19,21,-1:5,21,-1:3,21:4,71,21:19,-1:19,21,-1:5,21,-1:3,21:4,72,21:19,-1:19,21,-1:5,21,-1:3,21:12,73,21:11,-1:19,21,-1:5,21,-1:3,21:12,74,21:11,-1:19,21,-1:5,21,-1:3,21:3,75,21:20,-1:19,21,-1:5,21,-1:3,21:5,76,21:18,-1:19,21,-1:5,21,-1:3,21:17,77,21:6,-1:19,21,-1:5,21,-1:3,21:7,78,21:16,-1:19,21,-1:5,21,-1:3,21:17,79,21:6,-1:19,21,-1:5,21,-1:3,21:5,80,21:18,-1:19,21,-1:5,21,-1:3,21:4,81,21:19,-1:19,21,-1:5,21,-1:3,21:15,82,21:8,-1:19,21,-1:5,21,-1:3,21:3,83,21:20,-1:19,21,-1:5,21,-1:3,21:4,84,21:19,-1:19,21,-1:5,21,-1:3,21:10,156,21:2,89,21:4,90,21:5,-1:19,21,-1:5,21,-1:3,92,21:12,129,21:10,-1:19,21,-1:5,21,-1:3,21:17,93,21:6,-1:19,21,-1:5,21,-1:3,21:16,94,21:7,-1:19,21,-1:5,21,-1:3,95,21:23,-1:19,21,-1:5,21,-1:3,21:16,96,21:7,-1:19,21,-1:5,21,-1:3,21:18,97,21:5,-1:19,21,-1:5,21,-1:3,21:17,98,21:6,-1:19,21,-1:5,21,-1:3,21:12,99,21:11,-1:19,21,-1:5,21,-1:3,21:8,100,21,193,21:13,-1:19,21,-1:5,21,-1:3,101,21:23,-1:19,21,-1:5,21,-1:3,21:16,102,184,21:6,-1:19,21,-1:5,21,-1:3,103,21:23,-1:19,21,-1:5,21,-1:3,21:15,104,21:8,-1:19,21,-1:5,21,-1:3,21:13,105,21:10,-1:19,21,-1:5,21,-1:3,21:10,106,21:13,-1:19,21,-1:5,21,-1:3,21:10,107,21:13,-1:19,21,-1:5,21,-1:3,21:15,108,21:8,-1:19,21,-1:5,21,-1:3,21:15,109,21:8,-1:19,21,-1:5,21,-1:3,21:4,110,21:19,-1:19,21,-1:5,21,-1:3,21:13,111,21:10,-1:19,21,-1:5,21,-1:3,21:2,112,21:21,-1:19,21,-1:5,21,-1:3,21:2,113,21:21,-1:19,21,-1:5,21,-1:3,21:10,114,21:13,-1:19,21,-1:5,21,-1:3,21:4,115,21:19,-1:19,21,-1:5,21,-1:3,21:18,116,21:5,-1:19,21,-1:5,21,-1:3,21:4,117,21:19,-1:19,21,-1:5,21,-1:3,21:4,118,21:19,-1:19,21,-1:5,21,-1:3,21:10,119,21:13,-1:19,21,-1:5,21,-1:3,21:18,122,21:5,-1:19,21,-1:5,21,-1:3,123,21:6,124,21:5,155,21:10,-1:19,21,-1:5,21,-1:3,21:10,125,21,126,21:8,177,21:2,-1:19,21,-1:5,21,-1:3,21:13,127,21:10,-1:19,21,-1:5,21,-1:3,21:13,128,21:10,-1:19,21,-1:5,21,-1:3,21:4,130,21:19,-1:19,21,-1:5,21,-1:3,21:12,131,21:11,-1:19,21,-1:5,21,-1:3,21:13,132,21:10,-1:19,21,-1:5,21,-1:3,21:13,133,21:10,-1:19,21,-1:5,21,-1:3,21:8,134,21:7,192,21:7,-1:19,21,-1:5,21,-1:3,21:8,135,21:15,-1:19,21,-1:5,21,-1:3,21,136,21:22,-1:19,21,-1:5,21,-1:3,21:4,137,21:19,-1:19,21,-1:5,21,-1:3,21:18,138,21:5,-1:19,21,-1:5,21,-1:3,21:12,139,21:11,-1:19,21,-1:5,21,-1:3,21:4,140,21:19,-1:19,21,-1:5,21,-1:3,21:18,141,21:5,-1:19,21,-1:5,21,-1:3,21:17,142,21:6,-1:19,21,-1:5,21,-1:3,21:18,143,21:5,-1:19,21,-1:5,21,-1:3,21:3,144,21:20,-1:19,21,-1:5,21,-1:3,21:12,145,21:11,-1:19,21,-1:5,21,-1:3,21:17,146,21:6,-1:19,21,-1:5,21,-1:3,21:12,147,21:11,-1:19,21,-1:5,21,-1:3,21:8,148,21:15,-1:19,21,-1:5,21,-1:3,21:15,154,21:8,-1:19,21,-1:5,21,-1:3,21:7,157,179,21:8,180,21:2,181,21:3,-1:19,21,-1:5,21,-1:3,21:12,158,21:11,-1:19,21,-1:5,21,-1:3,21:7,159,21:16,-1:19,21,-1:5,21,-1:3,21:17,161,21:6,-1:19,21,-1:5,21,-1:3,21:6,191,21:10,162,21:6,-1:19,21,-1:5,21,-1:3,21:6,163,21:16,164,-1:19,21,-1:5,21,-1:3,21:15,165,21:8,-1:19,21,-1:5,21,-1:3,21:8,166,21:15,-1:19,21,-1:5,21,-1:3,167,21:23,-1:19,21,-1:5,21,-1:3,21:4,168,21:19,-1:19,21,-1:5,21,-1:3,21:8,169,21:15,-1:19,21,-1:5,21,-1:3,21:16,170,21:7,-1:19,21,-1:5,21,-1:3,21:6,171,21:17,-1:19,21,-1:5,21,-1:3,21:17,172,21:6,-1:19,21,-1:5,21,-1:3,21:4,178,21:19,-1:19,21,-1:5,21,-1:3,21:5,182,21:18,-1:19,21,-1:5,21,-1:3,21:14,183,21:9,-1:19,21,-1:5,21,-1:3,21:8,185,21:15,-1:19,21,-1:5,21,-1:3,21:8,186,21:15,-1:19,21,-1:5,21,-1:3,187,21:23,-1:19,21,-1:5,21,-1:3,21:22,190,21,-1:4");
	public java_cup.runtime.Symbol nextToken ()
		throws java.io.IOException {
		char yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			if (YYEOF != yy_lookahead) {
				yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YYEOF == yy_lookahead && true == yy_initial) {

	{
	 return tok(sym.EOF, null);
        }
				}
				else if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_to_mark();
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_pushback();
					}
					if (0 != (YY_START & yy_anchor)) {
						yy_move_start();
					}
					switch (yy_last_accept_state) {
					case 1:
						{ err("Illegal character: " + yytext()); }
					case -2:
						break;
					case 2:
						{newline();}
					case -3:
						break;
					case 3:
						{}
					case -4:
						break;
					case 4:
						{return tok(sym.POUND, null);}
					case -5:
						break;
					case 5:
						{return tok(sym.MODULUS, null);}
					case -6:
						break;
					case 6:
						{return tok(sym.BITWISEAND, null);}
					case -7:
						break;
					case 7:
						{return tok(sym.LPAREN, null);}
					case -8:
						break;
					case 8:
						{return tok(sym.RPAREN, null);}
					case -9:
						break;
					case 9:
						{return tok(sym.TIMES, null);}
					case -10:
						break;
					case 10:
						{return tok(sym.PLUS, null);}
					case -11:
						break;
					case 11:
						{return tok(sym.COMMA, null);}
					case -12:
						break;
					case 12:
						{return tok(sym.MINUS, null);}
					case -13:
						break;
					case 13:
						{return tok(sym.DOT, null);}
					case -14:
						break;
					case 14:
						{return tok(sym.DIVIDE, null);}
					case -15:
						break;
					case 15:
						{return tok(sym.DECIMAL_LITERAL, yytext());}
					case -16:
						break;
					case 16:
						{return tok(sym.COLON, null);}
					case -17:
						break;
					case 17:
						{return tok(sym.SEMICOLON, null);}
					case -18:
						break;
					case 18:
						{return tok(sym.LT, null);}
					case -19:
						break;
					case 19:
						{return tok(sym.EQ, null);}
					case -20:
						break;
					case 20:
						{return tok(sym.GT, null);}
					case -21:
						break;
					case 21:
						{ return tok(sym.ID, yytext()); }
					case -22:
						break;
					case 22:
						{return tok(sym.LBRACK, null);}
					case -23:
						break;
					case 23:
						{return tok(sym.RBRACK, null);}
					case -24:
						break;
					case 24:
						{return tok(sym.BWISEXOR, null);}
					case -25:
						break;
					case 25:
						{return tok(sym.LBRACE, null);}
					case -26:
						break;
					case 26:
						{return tok(sym.BWISEOR, null);}
					case -27:
						break;
					case 27:
						{return tok(sym.RBRACE, null);}
					case -28:
						break;
					case 28:
						{return tok(sym.BWISENOT, null);}
					case -29:
						break;
					case 29:
						{return tok(sym.NEQ, null);}
					case -30:
						break;
					case 30:
						{return tok(sym.MODASSIGN, null);}
					case -31:
						break;
					case 31:
						{return tok(sym.AND, null);}
					case -32:
						break;
					case 32:
						{return tok(sym.BWISEANDASSIGN, null);}
					case -33:
						break;
					case 33:
						{return tok(sym.MULASSIGN, null);}
					case -34:
						break;
					case 34:
						{return tok(sym.INCREMENT, null);}
					case -35:
						break;
					case 35:
						{return tok(sym.ADDASSIGN, null);}
					case -36:
						break;
					case 36:
						{return tok(sym.DECREMENT, null);}
					case -37:
						break;
					case 37:
						{return tok(sym.SUBASSIGN, null);}
					case -38:
						break;
					case 38:
						{return tok(sym.DIVASSIGN, null);}
					case -39:
						break;
					case 39:
						{return tok(sym.ASSIGN, null);}
					case -40:
						break;
					case 40:
						{return tok(sym.LSHIFT, null);}
					case -41:
						break;
					case 41:
						{return tok(sym.LE, null);}
					case -42:
						break;
					case 42:
						{return tok(sym.NEQ, null);}
					case -43:
						break;
					case 43:
						{return tok(sym.EQ, null);}
					case -44:
						break;
					case 44:
						{return tok(sym.GE, null);}
					case -45:
						break;
					case 45:
						{return tok(sym.RSHIFT, null);}
					case -46:
						break;
					case 46:
						{return tok(sym.BWISEXORASSIGN, null);}
					case -47:
						break;
					case 47:
						{return tok(sym.DO, null);}
					case -48:
						break;
					case 48:
						{return tok(sym.IF, null);}
					case -49:
						break;
					case 49:
						{return tok(sym.BWISEORASSIGN, null);}
					case -50:
						break;
					case 50:
						{return tok(sym.OR, null);}
					case -51:
						break;
					case 51:
						{return tok(sym.ELIPSES, null);}
					case -52:
						break;
					case 52:
						{return tok(sym.LSHIFTASSIGN, null);}
					case -53:
						break;
					case 53:
						{return tok(sym.RSHIFTASSIGN, null);}
					case -54:
						break;
					case 54:
						{return tok(sym.FOR, null);}
					case -55:
						break;
					case 55:
						{return tok(sym.FUN, null);}
					case -56:
						break;
					case 56:
						{return tok(sym.INT, null);}
					case -57:
						break;
					case 57:
						{return tok(sym.VAR, null);}
					case -58:
						break;
					case 58:
						{return tok(sym.AUTO, null);}
					case -59:
						break;
					case 59:
						{return tok(sym.CASE, null);}
					case -60:
						break;
					case 60:
						{return tok(sym.CHAR, null);}
					case -61:
						break;
					case 61:
						{return tok(sym.ELSE, null);}
					case -62:
						break;
					case 62:
						{return tok(sym.ENUM, null);}
					case -63:
						break;
					case 63:
						{return tok(sym.GOTO, null);}
					case -64:
						break;
					case 64:
						{return tok(sym.LONG, null);}
					case -65:
						break;
					case 65:
						{return tok(sym.VOID, null);}
					case -66:
						break;
					case 66:
						{return tok(sym.BREAK, null);}
					case -67:
						break;
					case 67:
						{return tok(sym.CONST, null);}
					case -68:
						break;
					case 68:
						{return tok(sym.FLOAT, null);}
					case -69:
						break;
					case 69:
						{return tok(sym.SHORT, null);}
					case -70:
						break;
					case 70:
						{return tok(sym.UNION, null);}
					case -71:
						break;
					case 71:
						{return tok(sym.WHILE, null);}
					case -72:
						break;
					case 72:
						{return tok(sym.DOUBLE, null);}
					case -73:
						break;
					case 73:
						{return tok(sym.EXTERN, null);}
					case -74:
						break;
					case 74:
						{return tok(sym.RETURN, null);}
					case -75:
						break;
					case 75:
						{return tok(sym.SIGNED, null);}
					case -76:
						break;
					case 76:
						{return tok(sym.SIZEOF, null);}
					case -77:
						break;
					case 77:
						{return tok(sym.STRUCT, null);}
					case -78:
						break;
					case 78:
						{return tok(sym.SWITCH, null);}
					case -79:
						break;
					case 79:
						{return tok(sym.DEFAULT, null);}
					case -80:
						break;
					case 80:
						{return tok(sym.TYPEDEF, null);}
					case -81:
						break;
					case 81:
						{return tok(sym.CONTINUE, null);}
					case -82:
						break;
					case 82:
						{return tok(sym.REGISTER, null);}
					case -83:
						break;
					case 83:
						{return tok(sym.UNSIGNED, null);}
					case -84:
						break;
					case 84:
						{return tok(sym.VOLATILE, null);}
					case -85:
						break;
					case 86:
						{ err("Illegal character: " + yytext()); }
					case -86:
						break;
					case 87:
						{ return tok(sym.ID, yytext()); }
					case -87:
						break;
					case 88:
						{ return tok(sym.ID, yytext()); }
					case -88:
						break;
					case 89:
						{ return tok(sym.ID, yytext()); }
					case -89:
						break;
					case 90:
						{ return tok(sym.ID, yytext()); }
					case -90:
						break;
					case 91:
						{ return tok(sym.ID, yytext()); }
					case -91:
						break;
					case 92:
						{ return tok(sym.ID, yytext()); }
					case -92:
						break;
					case 93:
						{ return tok(sym.ID, yytext()); }
					case -93:
						break;
					case 94:
						{ return tok(sym.ID, yytext()); }
					case -94:
						break;
					case 95:
						{ return tok(sym.ID, yytext()); }
					case -95:
						break;
					case 96:
						{ return tok(sym.ID, yytext()); }
					case -96:
						break;
					case 97:
						{ return tok(sym.ID, yytext()); }
					case -97:
						break;
					case 98:
						{ return tok(sym.ID, yytext()); }
					case -98:
						break;
					case 99:
						{ return tok(sym.ID, yytext()); }
					case -99:
						break;
					case 100:
						{ return tok(sym.ID, yytext()); }
					case -100:
						break;
					case 101:
						{ return tok(sym.ID, yytext()); }
					case -101:
						break;
					case 102:
						{ return tok(sym.ID, yytext()); }
					case -102:
						break;
					case 103:
						{ return tok(sym.ID, yytext()); }
					case -103:
						break;
					case 104:
						{ return tok(sym.ID, yytext()); }
					case -104:
						break;
					case 105:
						{ return tok(sym.ID, yytext()); }
					case -105:
						break;
					case 106:
						{ return tok(sym.ID, yytext()); }
					case -106:
						break;
					case 107:
						{ return tok(sym.ID, yytext()); }
					case -107:
						break;
					case 108:
						{ return tok(sym.ID, yytext()); }
					case -108:
						break;
					case 109:
						{ return tok(sym.ID, yytext()); }
					case -109:
						break;
					case 110:
						{ return tok(sym.ID, yytext()); }
					case -110:
						break;
					case 111:
						{ return tok(sym.ID, yytext()); }
					case -111:
						break;
					case 112:
						{ return tok(sym.ID, yytext()); }
					case -112:
						break;
					case 113:
						{ return tok(sym.ID, yytext()); }
					case -113:
						break;
					case 114:
						{ return tok(sym.ID, yytext()); }
					case -114:
						break;
					case 115:
						{ return tok(sym.ID, yytext()); }
					case -115:
						break;
					case 116:
						{ return tok(sym.ID, yytext()); }
					case -116:
						break;
					case 117:
						{ return tok(sym.ID, yytext()); }
					case -117:
						break;
					case 118:
						{ return tok(sym.ID, yytext()); }
					case -118:
						break;
					case 119:
						{ return tok(sym.ID, yytext()); }
					case -119:
						break;
					case 120:
						{ return tok(sym.ID, yytext()); }
					case -120:
						break;
					case 121:
						{ return tok(sym.ID, yytext()); }
					case -121:
						break;
					case 122:
						{ return tok(sym.ID, yytext()); }
					case -122:
						break;
					case 123:
						{ return tok(sym.ID, yytext()); }
					case -123:
						break;
					case 124:
						{ return tok(sym.ID, yytext()); }
					case -124:
						break;
					case 125:
						{ return tok(sym.ID, yytext()); }
					case -125:
						break;
					case 126:
						{ return tok(sym.ID, yytext()); }
					case -126:
						break;
					case 127:
						{ return tok(sym.ID, yytext()); }
					case -127:
						break;
					case 128:
						{ return tok(sym.ID, yytext()); }
					case -128:
						break;
					case 129:
						{ return tok(sym.ID, yytext()); }
					case -129:
						break;
					case 130:
						{ return tok(sym.ID, yytext()); }
					case -130:
						break;
					case 131:
						{ return tok(sym.ID, yytext()); }
					case -131:
						break;
					case 132:
						{ return tok(sym.ID, yytext()); }
					case -132:
						break;
					case 133:
						{ return tok(sym.ID, yytext()); }
					case -133:
						break;
					case 134:
						{ return tok(sym.ID, yytext()); }
					case -134:
						break;
					case 135:
						{ return tok(sym.ID, yytext()); }
					case -135:
						break;
					case 136:
						{ return tok(sym.ID, yytext()); }
					case -136:
						break;
					case 137:
						{ return tok(sym.ID, yytext()); }
					case -137:
						break;
					case 138:
						{ return tok(sym.ID, yytext()); }
					case -138:
						break;
					case 139:
						{ return tok(sym.ID, yytext()); }
					case -139:
						break;
					case 140:
						{ return tok(sym.ID, yytext()); }
					case -140:
						break;
					case 141:
						{ return tok(sym.ID, yytext()); }
					case -141:
						break;
					case 142:
						{ return tok(sym.ID, yytext()); }
					case -142:
						break;
					case 143:
						{ return tok(sym.ID, yytext()); }
					case -143:
						break;
					case 144:
						{ return tok(sym.ID, yytext()); }
					case -144:
						break;
					case 145:
						{ return tok(sym.ID, yytext()); }
					case -145:
						break;
					case 146:
						{ return tok(sym.ID, yytext()); }
					case -146:
						break;
					case 147:
						{ return tok(sym.ID, yytext()); }
					case -147:
						break;
					case 148:
						{ return tok(sym.ID, yytext()); }
					case -148:
						break;
					case 149:
						{ return tok(sym.ID, yytext()); }
					case -149:
						break;
					case 150:
						{ return tok(sym.ID, yytext()); }
					case -150:
						break;
					case 151:
						{ return tok(sym.ID, yytext()); }
					case -151:
						break;
					case 152:
						{ return tok(sym.ID, yytext()); }
					case -152:
						break;
					case 153:
						{ return tok(sym.ID, yytext()); }
					case -153:
						break;
					case 154:
						{ return tok(sym.ID, yytext()); }
					case -154:
						break;
					case 155:
						{ return tok(sym.ID, yytext()); }
					case -155:
						break;
					case 156:
						{ return tok(sym.ID, yytext()); }
					case -156:
						break;
					case 157:
						{ return tok(sym.ID, yytext()); }
					case -157:
						break;
					case 158:
						{ return tok(sym.ID, yytext()); }
					case -158:
						break;
					case 159:
						{ return tok(sym.ID, yytext()); }
					case -159:
						break;
					case 160:
						{ return tok(sym.ID, yytext()); }
					case -160:
						break;
					case 161:
						{ return tok(sym.ID, yytext()); }
					case -161:
						break;
					case 162:
						{ return tok(sym.ID, yytext()); }
					case -162:
						break;
					case 163:
						{ return tok(sym.ID, yytext()); }
					case -163:
						break;
					case 164:
						{ return tok(sym.ID, yytext()); }
					case -164:
						break;
					case 165:
						{ return tok(sym.ID, yytext()); }
					case -165:
						break;
					case 166:
						{ return tok(sym.ID, yytext()); }
					case -166:
						break;
					case 167:
						{ return tok(sym.ID, yytext()); }
					case -167:
						break;
					case 168:
						{ return tok(sym.ID, yytext()); }
					case -168:
						break;
					case 169:
						{ return tok(sym.ID, yytext()); }
					case -169:
						break;
					case 170:
						{ return tok(sym.ID, yytext()); }
					case -170:
						break;
					case 171:
						{ return tok(sym.ID, yytext()); }
					case -171:
						break;
					case 172:
						{ return tok(sym.ID, yytext()); }
					case -172:
						break;
					case 173:
						{ return tok(sym.ID, yytext()); }
					case -173:
						break;
					case 174:
						{ return tok(sym.ID, yytext()); }
					case -174:
						break;
					case 175:
						{ return tok(sym.ID, yytext()); }
					case -175:
						break;
					case 176:
						{ return tok(sym.ID, yytext()); }
					case -176:
						break;
					case 177:
						{ return tok(sym.ID, yytext()); }
					case -177:
						break;
					case 178:
						{ return tok(sym.ID, yytext()); }
					case -178:
						break;
					case 179:
						{ return tok(sym.ID, yytext()); }
					case -179:
						break;
					case 180:
						{ return tok(sym.ID, yytext()); }
					case -180:
						break;
					case 181:
						{ return tok(sym.ID, yytext()); }
					case -181:
						break;
					case 182:
						{ return tok(sym.ID, yytext()); }
					case -182:
						break;
					case 183:
						{ return tok(sym.ID, yytext()); }
					case -183:
						break;
					case 184:
						{ return tok(sym.ID, yytext()); }
					case -184:
						break;
					case 185:
						{ return tok(sym.ID, yytext()); }
					case -185:
						break;
					case 186:
						{ return tok(sym.ID, yytext()); }
					case -186:
						break;
					case 187:
						{ return tok(sym.ID, yytext()); }
					case -187:
						break;
					case 188:
						{ return tok(sym.ID, yytext()); }
					case -188:
						break;
					case 189:
						{ return tok(sym.ID, yytext()); }
					case -189:
						break;
					case 190:
						{ return tok(sym.ID, yytext()); }
					case -190:
						break;
					case 191:
						{ return tok(sym.ID, yytext()); }
					case -191:
						break;
					case 192:
						{ return tok(sym.ID, yytext()); }
					case -192:
						break;
					case 193:
						{ return tok(sym.ID, yytext()); }
					case -193:
						break;
					case 194:
						{ return tok(sym.ID, yytext()); }
					case -194:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
					}
				}
			}
		}
	}
}
