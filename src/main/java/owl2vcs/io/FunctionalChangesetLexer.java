// $ANTLR 3.5-rc-1 R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g 2012-12-26 19:11:17

package owl2vcs.io;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class FunctionalChangesetLexer extends Lexer {
	public static final int EOF=-1;
	public static final int T__18=18;
	public static final int T__19=19;
	public static final int T__20=20;
	public static final int T__21=21;
	public static final int T__22=22;
	public static final int T__23=23;
	public static final int T__24=24;
	public static final int T__25=25;
	public static final int T__26=26;
	public static final int T__27=27;
	public static final int T__28=28;
	public static final int T__29=29;
	public static final int T__30=30;
	public static final int T__31=31;
	public static final int FULLIRI=4;
	public static final int INT=5;
	public static final int LANG_TAG=6;
	public static final int NAME=7;
	public static final int NODEID=8;
	public static final int PNAME_LN=9;
	public static final int PNAME_NS=10;
	public static final int PN_CHARS=11;
	public static final int PN_CHARS_BASE=12;
	public static final int PN_CHARS_U=13;
	public static final int PN_LOCAL=14;
	public static final int PN_PREFIX=15;
	public static final int QUOTED_STRING=16;
	public static final int WS=17;




	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public FunctionalChangesetLexer() {} 
	public FunctionalChangesetLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public FunctionalChangesetLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g"; }

	// $ANTLR start "T__18"
	public final void mT__18() throws RecognitionException {
		try {
			int _type = T__18;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:14:7: ( '# Prefix' )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:14:9: '# Prefix'
			{
			match("# Prefix"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__18"

	// $ANTLR start "T__19"
	public final void mT__19() throws RecognitionException {
		try {
			int _type = T__19;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:15:7: ( '(' )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:15:9: '('
			{
			match('('); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__19"

	// $ANTLR start "T__20"
	public final void mT__20() throws RecognitionException {
		try {
			int _type = T__20;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:16:7: ( ')' )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:16:9: ')'
			{
			match(')'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__20"

	// $ANTLR start "T__21"
	public final void mT__21() throws RecognitionException {
		try {
			int _type = T__21;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:17:7: ( '* OntologyFormat' )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:17:9: '* OntologyFormat'
			{
			match("* OntologyFormat"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__21"

	// $ANTLR start "T__22"
	public final void mT__22() throws RecognitionException {
		try {
			int _type = T__22;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:18:7: ( '* OntologyID(' )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:18:9: '* OntologyID('
			{
			match("* OntologyID("); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__22"

	// $ANTLR start "T__23"
	public final void mT__23() throws RecognitionException {
		try {
			int _type = T__23;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:19:7: ( '* Prefix' )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:19:9: '* Prefix'
			{
			match("* Prefix"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__23"

	// $ANTLR start "T__24"
	public final void mT__24() throws RecognitionException {
		try {
			int _type = T__24;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:20:7: ( '+ ' )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:20:9: '+ '
			{
			match("+ "); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__24"

	// $ANTLR start "T__25"
	public final void mT__25() throws RecognitionException {
		try {
			int _type = T__25;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:21:7: ( '+ Prefix' )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:21:9: '+ Prefix'
			{
			match("+ Prefix"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__25"

	// $ANTLR start "T__26"
	public final void mT__26() throws RecognitionException {
		try {
			int _type = T__26;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:22:7: ( '- ' )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:22:9: '- '
			{
			match("- "); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__26"

	// $ANTLR start "T__27"
	public final void mT__27() throws RecognitionException {
		try {
			int _type = T__27;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:23:7: ( '- Prefix' )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:23:9: '- Prefix'
			{
			match("- Prefix"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__27"

	// $ANTLR start "T__28"
	public final void mT__28() throws RecognitionException {
		try {
			int _type = T__28;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:24:7: ( '=' )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:24:9: '='
			{
			match('='); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__28"

	// $ANTLR start "T__29"
	public final void mT__29() throws RecognitionException {
		try {
			int _type = T__29;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:25:7: ( 'Annotation' )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:25:9: 'Annotation'
			{
			match("Annotation"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__29"

	// $ANTLR start "T__30"
	public final void mT__30() throws RecognitionException {
		try {
			int _type = T__30;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:26:7: ( 'Import' )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:26:9: 'Import'
			{
			match("Import"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__30"

	// $ANTLR start "T__31"
	public final void mT__31() throws RecognitionException {
		try {
			int _type = T__31;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:27:7: ( '^^' )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:27:9: '^^'
			{
			match("^^"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__31"

	// $ANTLR start "LANG_TAG"
	public final void mLANG_TAG() throws RecognitionException {
		try {
			int _type = LANG_TAG;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:310:3: ( '@' ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' | '-' )+ )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:310:5: '@' ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' | '-' )+
			{
			match('@'); 
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:311:5: ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' | '-' )+
			int cnt1=0;
			loop1:
			do {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( (LA1_0=='-'||(LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:
					{
					if ( input.LA(1)=='-'||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt1 >= 1 ) break loop1;
						EarlyExitException eee =
							new EarlyExitException(1, input);
						throw eee;
				}
				cnt1++;
			} while (true);

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LANG_TAG"

	// $ANTLR start "INT"
	public final void mINT() throws RecognitionException {
		try {
			int _type = INT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:318:3: ( ( '0' .. '9' )+ )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:318:5: ( '0' .. '9' )+
			{
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:318:5: ( '0' .. '9' )+
			int cnt2=0;
			loop2:
			do {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( ((LA2_0 >= '0' && LA2_0 <= '9')) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt2 >= 1 ) break loop2;
						EarlyExitException eee =
							new EarlyExitException(2, input);
						throw eee;
				}
				cnt2++;
			} while (true);

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INT"

	// $ANTLR start "NAME"
	public final void mNAME() throws RecognitionException {
		try {
			int _type = NAME;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:322:3: ( 'A' .. 'Z' ( 'A' .. 'Z' | 'a' .. 'z' )+ )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:322:5: 'A' .. 'Z' ( 'A' .. 'Z' | 'a' .. 'z' )+
			{
			matchRange('A','Z'); 
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:323:5: ( 'A' .. 'Z' | 'a' .. 'z' )+
			int cnt3=0;
			loop3:
			do {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( ((LA3_0 >= 'A' && LA3_0 <= 'Z')||(LA3_0 >= 'a' && LA3_0 <= 'z')) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:
					{
					if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt3 >= 1 ) break loop3;
						EarlyExitException eee =
							new EarlyExitException(3, input);
						throw eee;
				}
				cnt3++;
			} while (true);

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NAME"

	// $ANTLR start "QUOTED_STRING"
	public final void mQUOTED_STRING() throws RecognitionException {
		try {
			int _type = QUOTED_STRING;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:329:3: ( '\"' ( '\\\\' '\"' | '\\\\' '\\\\' |~ ( '\"' | '\\\\' ) )* '\"' )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:329:5: '\"' ( '\\\\' '\"' | '\\\\' '\\\\' |~ ( '\"' | '\\\\' ) )* '\"'
			{
			match('\"'); 
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:330:5: ( '\\\\' '\"' | '\\\\' '\\\\' |~ ( '\"' | '\\\\' ) )*
			loop4:
			do {
				int alt4=4;
				int LA4_0 = input.LA(1);
				if ( (LA4_0=='\\') ) {
					int LA4_2 = input.LA(2);
					if ( (LA4_2=='\"') ) {
						alt4=1;
					}
					else if ( (LA4_2=='\\') ) {
						alt4=2;
					}

				}
				else if ( ((LA4_0 >= '\u0000' && LA4_0 <= '!')||(LA4_0 >= '#' && LA4_0 <= '[')||(LA4_0 >= ']' && LA4_0 <= '\uFFFF')) ) {
					alt4=3;
				}

				switch (alt4) {
				case 1 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:330:7: '\\\\' '\"'
					{
					match('\\'); 
					match('\"'); 
					}
					break;
				case 2 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:331:7: '\\\\' '\\\\'
					{
					match('\\'); 
					match('\\'); 
					}
					break;
				case 3 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:332:7: ~ ( '\"' | '\\\\' )
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop4;
				}
			} while (true);

			match('\"'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "QUOTED_STRING"

	// $ANTLR start "FULLIRI"
	public final void mFULLIRI() throws RecognitionException {
		try {
			int _type = FULLIRI;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:338:3: ( '<' ( options {greedy=false; } :~ '>' )* '>' )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:338:5: '<' ( options {greedy=false; } :~ '>' )* '>'
			{
			match('<'); 
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:339:5: ( options {greedy=false; } :~ '>' )*
			loop5:
			do {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( ((LA5_0 >= '\u0000' && LA5_0 <= '=')||(LA5_0 >= '?' && LA5_0 <= '\uFFFF')) ) {
					alt5=1;
				}
				else if ( (LA5_0=='>') ) {
					alt5=2;
				}

				switch (alt5) {
				case 1 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:340:7: ~ '>'
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '=')||(input.LA(1) >= '?' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop5;
				}
			} while (true);

			match('>'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FULLIRI"

	// $ANTLR start "PNAME_LN"
	public final void mPNAME_LN() throws RecognitionException {
		try {
			int _type = PNAME_LN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:346:3: ( PNAME_NS PN_LOCAL )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:346:5: PNAME_NS PN_LOCAL
			{
			mPNAME_NS(); 

			mPN_LOCAL(); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PNAME_LN"

	// $ANTLR start "NODEID"
	public final void mNODEID() throws RecognitionException {
		try {
			int _type = NODEID;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:349:3: ( '_:' PN_LOCAL )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:349:5: '_:' PN_LOCAL
			{
			match("_:"); 

			mPN_LOCAL(); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NODEID"

	// $ANTLR start "PNAME_NS"
	public final void mPNAME_NS() throws RecognitionException {
		try {
			int _type = PNAME_NS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:352:3: ( ( PN_PREFIX )? ':' )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:352:5: ( PN_PREFIX )? ':'
			{
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:352:5: ( PN_PREFIX )?
			int alt6=2;
			int LA6_0 = input.LA(1);
			if ( ((LA6_0 >= 'A' && LA6_0 <= 'Z')||(LA6_0 >= 'a' && LA6_0 <= 'z')||(LA6_0 >= '\u00C0' && LA6_0 <= '\u00D6')||(LA6_0 >= '\u00D8' && LA6_0 <= '\u00F6')||(LA6_0 >= '\u00F8' && LA6_0 <= '\u02FF')||(LA6_0 >= '\u0370' && LA6_0 <= '\u037D')||(LA6_0 >= '\u037F' && LA6_0 <= '\u1FFF')||(LA6_0 >= '\u200C' && LA6_0 <= '\u200D')||(LA6_0 >= '\u2070' && LA6_0 <= '\u218F')||(LA6_0 >= '\u2C00' && LA6_0 <= '\u2FEF')||(LA6_0 >= '\u3001' && LA6_0 <= '\uD7FF')||(LA6_0 >= '\uF900' && LA6_0 <= '\uFDCF')||(LA6_0 >= '\uFDF0' && LA6_0 <= '\uFFFD')) ) {
				alt6=1;
			}
			switch (alt6) {
				case 1 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:352:5: PN_PREFIX
					{
					mPN_PREFIX(); 

					}
					break;

			}

			match(':'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PNAME_NS"

	// $ANTLR start "PN_LOCAL"
	public final void mPN_LOCAL() throws RecognitionException {
		try {
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:355:3: ( ( PN_CHARS_U | '0' .. '9' ) ( ( PN_CHARS | '.' )* PN_CHARS )? )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:355:5: ( PN_CHARS_U | '0' .. '9' ) ( ( PN_CHARS | '.' )* PN_CHARS )?
			{
			if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z')||(input.LA(1) >= '\u00C0' && input.LA(1) <= '\u00D6')||(input.LA(1) >= '\u00D8' && input.LA(1) <= '\u00F6')||(input.LA(1) >= '\u00F8' && input.LA(1) <= '\u02FF')||(input.LA(1) >= '\u0370' && input.LA(1) <= '\u037D')||(input.LA(1) >= '\u037F' && input.LA(1) <= '\u1FFF')||(input.LA(1) >= '\u200C' && input.LA(1) <= '\u200D')||(input.LA(1) >= '\u2070' && input.LA(1) <= '\u218F')||(input.LA(1) >= '\u2C00' && input.LA(1) <= '\u2FEF')||(input.LA(1) >= '\u3001' && input.LA(1) <= '\uD7FF')||(input.LA(1) >= '\uF900' && input.LA(1) <= '\uFDCF')||(input.LA(1) >= '\uFDF0' && input.LA(1) <= '\uFFFD') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:355:31: ( ( PN_CHARS | '.' )* PN_CHARS )?
			int alt8=2;
			int LA8_0 = input.LA(1);
			if ( ((LA8_0 >= '-' && LA8_0 <= '.')||(LA8_0 >= '0' && LA8_0 <= '9')||(LA8_0 >= 'A' && LA8_0 <= 'Z')||LA8_0=='_'||(LA8_0 >= 'a' && LA8_0 <= 'z')||LA8_0=='\u00B7'||(LA8_0 >= '\u00C0' && LA8_0 <= '\u00D6')||(LA8_0 >= '\u00D8' && LA8_0 <= '\u00F6')||(LA8_0 >= '\u00F8' && LA8_0 <= '\u037D')||(LA8_0 >= '\u037F' && LA8_0 <= '\u1FFF')||(LA8_0 >= '\u200C' && LA8_0 <= '\u200D')||(LA8_0 >= '\u203F' && LA8_0 <= '\u2040')||(LA8_0 >= '\u2070' && LA8_0 <= '\u218F')||(LA8_0 >= '\u2C00' && LA8_0 <= '\u2FEF')||(LA8_0 >= '\u3001' && LA8_0 <= '\uD7FF')||(LA8_0 >= '\uF900' && LA8_0 <= '\uFDCF')||(LA8_0 >= '\uFDF0' && LA8_0 <= '\uFFFD')) ) {
				alt8=1;
			}
			switch (alt8) {
				case 1 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:355:33: ( PN_CHARS | '.' )* PN_CHARS
					{
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:355:33: ( PN_CHARS | '.' )*
					loop7:
					do {
						int alt7=2;
						int LA7_0 = input.LA(1);
						if ( (LA7_0=='-'||(LA7_0 >= '0' && LA7_0 <= '9')||(LA7_0 >= 'A' && LA7_0 <= 'Z')||LA7_0=='_'||(LA7_0 >= 'a' && LA7_0 <= 'z')||LA7_0=='\u00B7'||(LA7_0 >= '\u00C0' && LA7_0 <= '\u00D6')||(LA7_0 >= '\u00D8' && LA7_0 <= '\u00F6')||(LA7_0 >= '\u00F8' && LA7_0 <= '\u037D')||(LA7_0 >= '\u037F' && LA7_0 <= '\u1FFF')||(LA7_0 >= '\u200C' && LA7_0 <= '\u200D')||(LA7_0 >= '\u203F' && LA7_0 <= '\u2040')||(LA7_0 >= '\u2070' && LA7_0 <= '\u218F')||(LA7_0 >= '\u2C00' && LA7_0 <= '\u2FEF')||(LA7_0 >= '\u3001' && LA7_0 <= '\uD7FF')||(LA7_0 >= '\uF900' && LA7_0 <= '\uFDCF')||(LA7_0 >= '\uFDF0' && LA7_0 <= '\uFFFD')) ) {
							int LA7_1 = input.LA(2);
							if ( ((LA7_1 >= '-' && LA7_1 <= '.')||(LA7_1 >= '0' && LA7_1 <= '9')||(LA7_1 >= 'A' && LA7_1 <= 'Z')||LA7_1=='_'||(LA7_1 >= 'a' && LA7_1 <= 'z')||LA7_1=='\u00B7'||(LA7_1 >= '\u00C0' && LA7_1 <= '\u00D6')||(LA7_1 >= '\u00D8' && LA7_1 <= '\u00F6')||(LA7_1 >= '\u00F8' && LA7_1 <= '\u037D')||(LA7_1 >= '\u037F' && LA7_1 <= '\u1FFF')||(LA7_1 >= '\u200C' && LA7_1 <= '\u200D')||(LA7_1 >= '\u203F' && LA7_1 <= '\u2040')||(LA7_1 >= '\u2070' && LA7_1 <= '\u218F')||(LA7_1 >= '\u2C00' && LA7_1 <= '\u2FEF')||(LA7_1 >= '\u3001' && LA7_1 <= '\uD7FF')||(LA7_1 >= '\uF900' && LA7_1 <= '\uFDCF')||(LA7_1 >= '\uFDF0' && LA7_1 <= '\uFFFD')) ) {
								alt7=1;
							}

						}
						else if ( (LA7_0=='.') ) {
							alt7=1;
						}

						switch (alt7) {
						case 1 :
							// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:
							{
							if ( (input.LA(1) >= '-' && input.LA(1) <= '.')||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z')||input.LA(1)=='\u00B7'||(input.LA(1) >= '\u00C0' && input.LA(1) <= '\u00D6')||(input.LA(1) >= '\u00D8' && input.LA(1) <= '\u00F6')||(input.LA(1) >= '\u00F8' && input.LA(1) <= '\u037D')||(input.LA(1) >= '\u037F' && input.LA(1) <= '\u1FFF')||(input.LA(1) >= '\u200C' && input.LA(1) <= '\u200D')||(input.LA(1) >= '\u203F' && input.LA(1) <= '\u2040')||(input.LA(1) >= '\u2070' && input.LA(1) <= '\u218F')||(input.LA(1) >= '\u2C00' && input.LA(1) <= '\u2FEF')||(input.LA(1) >= '\u3001' && input.LA(1) <= '\uD7FF')||(input.LA(1) >= '\uF900' && input.LA(1) <= '\uFDCF')||(input.LA(1) >= '\uFDF0' && input.LA(1) <= '\uFFFD') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							break loop7;
						}
					} while (true);

					mPN_CHARS(); 

					}
					break;

			}

			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PN_LOCAL"

	// $ANTLR start "PN_PREFIX"
	public final void mPN_PREFIX() throws RecognitionException {
		try {
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:358:3: ( PN_CHARS_BASE ( ( PN_CHARS | '.' )* PN_CHARS )? )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:358:5: PN_CHARS_BASE ( ( PN_CHARS | '.' )* PN_CHARS )?
			{
			mPN_CHARS_BASE(); 

			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:358:19: ( ( PN_CHARS | '.' )* PN_CHARS )?
			int alt10=2;
			int LA10_0 = input.LA(1);
			if ( ((LA10_0 >= '-' && LA10_0 <= '.')||(LA10_0 >= '0' && LA10_0 <= '9')||(LA10_0 >= 'A' && LA10_0 <= 'Z')||LA10_0=='_'||(LA10_0 >= 'a' && LA10_0 <= 'z')||LA10_0=='\u00B7'||(LA10_0 >= '\u00C0' && LA10_0 <= '\u00D6')||(LA10_0 >= '\u00D8' && LA10_0 <= '\u00F6')||(LA10_0 >= '\u00F8' && LA10_0 <= '\u037D')||(LA10_0 >= '\u037F' && LA10_0 <= '\u1FFF')||(LA10_0 >= '\u200C' && LA10_0 <= '\u200D')||(LA10_0 >= '\u203F' && LA10_0 <= '\u2040')||(LA10_0 >= '\u2070' && LA10_0 <= '\u218F')||(LA10_0 >= '\u2C00' && LA10_0 <= '\u2FEF')||(LA10_0 >= '\u3001' && LA10_0 <= '\uD7FF')||(LA10_0 >= '\uF900' && LA10_0 <= '\uFDCF')||(LA10_0 >= '\uFDF0' && LA10_0 <= '\uFFFD')) ) {
				alt10=1;
			}
			switch (alt10) {
				case 1 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:358:21: ( PN_CHARS | '.' )* PN_CHARS
					{
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:358:21: ( PN_CHARS | '.' )*
					loop9:
					do {
						int alt9=2;
						int LA9_0 = input.LA(1);
						if ( (LA9_0=='-'||(LA9_0 >= '0' && LA9_0 <= '9')||(LA9_0 >= 'A' && LA9_0 <= 'Z')||LA9_0=='_'||(LA9_0 >= 'a' && LA9_0 <= 'z')||LA9_0=='\u00B7'||(LA9_0 >= '\u00C0' && LA9_0 <= '\u00D6')||(LA9_0 >= '\u00D8' && LA9_0 <= '\u00F6')||(LA9_0 >= '\u00F8' && LA9_0 <= '\u037D')||(LA9_0 >= '\u037F' && LA9_0 <= '\u1FFF')||(LA9_0 >= '\u200C' && LA9_0 <= '\u200D')||(LA9_0 >= '\u203F' && LA9_0 <= '\u2040')||(LA9_0 >= '\u2070' && LA9_0 <= '\u218F')||(LA9_0 >= '\u2C00' && LA9_0 <= '\u2FEF')||(LA9_0 >= '\u3001' && LA9_0 <= '\uD7FF')||(LA9_0 >= '\uF900' && LA9_0 <= '\uFDCF')||(LA9_0 >= '\uFDF0' && LA9_0 <= '\uFFFD')) ) {
							int LA9_1 = input.LA(2);
							if ( ((LA9_1 >= '-' && LA9_1 <= '.')||(LA9_1 >= '0' && LA9_1 <= '9')||(LA9_1 >= 'A' && LA9_1 <= 'Z')||LA9_1=='_'||(LA9_1 >= 'a' && LA9_1 <= 'z')||LA9_1=='\u00B7'||(LA9_1 >= '\u00C0' && LA9_1 <= '\u00D6')||(LA9_1 >= '\u00D8' && LA9_1 <= '\u00F6')||(LA9_1 >= '\u00F8' && LA9_1 <= '\u037D')||(LA9_1 >= '\u037F' && LA9_1 <= '\u1FFF')||(LA9_1 >= '\u200C' && LA9_1 <= '\u200D')||(LA9_1 >= '\u203F' && LA9_1 <= '\u2040')||(LA9_1 >= '\u2070' && LA9_1 <= '\u218F')||(LA9_1 >= '\u2C00' && LA9_1 <= '\u2FEF')||(LA9_1 >= '\u3001' && LA9_1 <= '\uD7FF')||(LA9_1 >= '\uF900' && LA9_1 <= '\uFDCF')||(LA9_1 >= '\uFDF0' && LA9_1 <= '\uFFFD')) ) {
								alt9=1;
							}

						}
						else if ( (LA9_0=='.') ) {
							alt9=1;
						}

						switch (alt9) {
						case 1 :
							// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:
							{
							if ( (input.LA(1) >= '-' && input.LA(1) <= '.')||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z')||input.LA(1)=='\u00B7'||(input.LA(1) >= '\u00C0' && input.LA(1) <= '\u00D6')||(input.LA(1) >= '\u00D8' && input.LA(1) <= '\u00F6')||(input.LA(1) >= '\u00F8' && input.LA(1) <= '\u037D')||(input.LA(1) >= '\u037F' && input.LA(1) <= '\u1FFF')||(input.LA(1) >= '\u200C' && input.LA(1) <= '\u200D')||(input.LA(1) >= '\u203F' && input.LA(1) <= '\u2040')||(input.LA(1) >= '\u2070' && input.LA(1) <= '\u218F')||(input.LA(1) >= '\u2C00' && input.LA(1) <= '\u2FEF')||(input.LA(1) >= '\u3001' && input.LA(1) <= '\uD7FF')||(input.LA(1) >= '\uF900' && input.LA(1) <= '\uFDCF')||(input.LA(1) >= '\uFDF0' && input.LA(1) <= '\uFFFD') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							break loop9;
						}
					} while (true);

					mPN_CHARS(); 

					}
					break;

			}

			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PN_PREFIX"

	// $ANTLR start "PN_CHARS"
	public final void mPN_CHARS() throws RecognitionException {
		try {
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:361:3: ( PN_CHARS_U | '-' | '0' .. '9' | '\\u00B7' | '\\u0300' .. '\\u036F' | '\\u203F' .. '\\u2040' )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:
			{
			if ( input.LA(1)=='-'||(input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z')||input.LA(1)=='\u00B7'||(input.LA(1) >= '\u00C0' && input.LA(1) <= '\u00D6')||(input.LA(1) >= '\u00D8' && input.LA(1) <= '\u00F6')||(input.LA(1) >= '\u00F8' && input.LA(1) <= '\u037D')||(input.LA(1) >= '\u037F' && input.LA(1) <= '\u1FFF')||(input.LA(1) >= '\u200C' && input.LA(1) <= '\u200D')||(input.LA(1) >= '\u203F' && input.LA(1) <= '\u2040')||(input.LA(1) >= '\u2070' && input.LA(1) <= '\u218F')||(input.LA(1) >= '\u2C00' && input.LA(1) <= '\u2FEF')||(input.LA(1) >= '\u3001' && input.LA(1) <= '\uD7FF')||(input.LA(1) >= '\uF900' && input.LA(1) <= '\uFDCF')||(input.LA(1) >= '\uFDF0' && input.LA(1) <= '\uFFFD') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PN_CHARS"

	// $ANTLR start "PN_CHARS_U"
	public final void mPN_CHARS_U() throws RecognitionException {
		try {
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:370:3: ( PN_CHARS_BASE | '_' )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z')||(input.LA(1) >= '\u00C0' && input.LA(1) <= '\u00D6')||(input.LA(1) >= '\u00D8' && input.LA(1) <= '\u00F6')||(input.LA(1) >= '\u00F8' && input.LA(1) <= '\u02FF')||(input.LA(1) >= '\u0370' && input.LA(1) <= '\u037D')||(input.LA(1) >= '\u037F' && input.LA(1) <= '\u1FFF')||(input.LA(1) >= '\u200C' && input.LA(1) <= '\u200D')||(input.LA(1) >= '\u2070' && input.LA(1) <= '\u218F')||(input.LA(1) >= '\u2C00' && input.LA(1) <= '\u2FEF')||(input.LA(1) >= '\u3001' && input.LA(1) <= '\uD7FF')||(input.LA(1) >= '\uF900' && input.LA(1) <= '\uFDCF')||(input.LA(1) >= '\uFDF0' && input.LA(1) <= '\uFFFD') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PN_CHARS_U"

	// $ANTLR start "PN_CHARS_BASE"
	public final void mPN_CHARS_BASE() throws RecognitionException {
		try {
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:375:3: ( 'A' .. 'Z' | 'a' .. 'z' | '\\u00C0' .. '\\u00D6' | '\\u00D8' .. '\\u00F6' | '\\u00F8' .. '\\u02FF' | '\\u0370' .. '\\u037D' | '\\u037F' .. '\\u1FFF' | '\\u200C' .. '\\u200D' | '\\u2070' .. '\\u218F' | '\\u2C00' .. '\\u2FEF' | '\\u3001' .. '\\uD7FF' | '\\uF900' .. '\\uFDCF' | '\\uFDF0' .. '\\uFFFD' )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z')||(input.LA(1) >= '\u00C0' && input.LA(1) <= '\u00D6')||(input.LA(1) >= '\u00D8' && input.LA(1) <= '\u00F6')||(input.LA(1) >= '\u00F8' && input.LA(1) <= '\u02FF')||(input.LA(1) >= '\u0370' && input.LA(1) <= '\u037D')||(input.LA(1) >= '\u037F' && input.LA(1) <= '\u1FFF')||(input.LA(1) >= '\u200C' && input.LA(1) <= '\u200D')||(input.LA(1) >= '\u2070' && input.LA(1) <= '\u218F')||(input.LA(1) >= '\u2C00' && input.LA(1) <= '\u2FEF')||(input.LA(1) >= '\u3001' && input.LA(1) <= '\uD7FF')||(input.LA(1) >= '\uF900' && input.LA(1) <= '\uFDCF')||(input.LA(1) >= '\uFDF0' && input.LA(1) <= '\uFFFD') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PN_CHARS_BASE"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:390:4: ( ( ' ' | '\\t' | '\\n' | '\\r' | '\\f' )+ )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:390:6: ( ' ' | '\\t' | '\\n' | '\\r' | '\\f' )+
			{
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:390:6: ( ' ' | '\\t' | '\\n' | '\\r' | '\\f' )+
			int cnt11=0;
			loop11:
			do {
				int alt11=2;
				int LA11_0 = input.LA(1);
				if ( ((LA11_0 >= '\t' && LA11_0 <= '\n')||(LA11_0 >= '\f' && LA11_0 <= '\r')||LA11_0==' ') ) {
					alt11=1;
				}

				switch (alt11) {
				case 1 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:
					{
					if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||(input.LA(1) >= '\f' && input.LA(1) <= '\r')||input.LA(1)==' ' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt11 >= 1 ) break loop11;
						EarlyExitException eee =
							new EarlyExitException(11, input);
						throw eee;
				}
				cnt11++;
			} while (true);

			_channel = HIDDEN;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	@Override
	public void mTokens() throws RecognitionException {
		// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:1:8: ( T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | LANG_TAG | INT | NAME | QUOTED_STRING | FULLIRI | PNAME_LN | NODEID | PNAME_NS | WS )
		int alt12=23;
		alt12 = dfa12.predict(input);
		switch (alt12) {
			case 1 :
				// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:1:10: T__18
				{
				mT__18(); 

				}
				break;
			case 2 :
				// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:1:16: T__19
				{
				mT__19(); 

				}
				break;
			case 3 :
				// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:1:22: T__20
				{
				mT__20(); 

				}
				break;
			case 4 :
				// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:1:28: T__21
				{
				mT__21(); 

				}
				break;
			case 5 :
				// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:1:34: T__22
				{
				mT__22(); 

				}
				break;
			case 6 :
				// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:1:40: T__23
				{
				mT__23(); 

				}
				break;
			case 7 :
				// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:1:46: T__24
				{
				mT__24(); 

				}
				break;
			case 8 :
				// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:1:52: T__25
				{
				mT__25(); 

				}
				break;
			case 9 :
				// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:1:58: T__26
				{
				mT__26(); 

				}
				break;
			case 10 :
				// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:1:64: T__27
				{
				mT__27(); 

				}
				break;
			case 11 :
				// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:1:70: T__28
				{
				mT__28(); 

				}
				break;
			case 12 :
				// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:1:76: T__29
				{
				mT__29(); 

				}
				break;
			case 13 :
				// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:1:82: T__30
				{
				mT__30(); 

				}
				break;
			case 14 :
				// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:1:88: T__31
				{
				mT__31(); 

				}
				break;
			case 15 :
				// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:1:94: LANG_TAG
				{
				mLANG_TAG(); 

				}
				break;
			case 16 :
				// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:1:103: INT
				{
				mINT(); 

				}
				break;
			case 17 :
				// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:1:107: NAME
				{
				mNAME(); 

				}
				break;
			case 18 :
				// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:1:112: QUOTED_STRING
				{
				mQUOTED_STRING(); 

				}
				break;
			case 19 :
				// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:1:126: FULLIRI
				{
				mFULLIRI(); 

				}
				break;
			case 20 :
				// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:1:134: PNAME_LN
				{
				mPNAME_LN(); 

				}
				break;
			case 21 :
				// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:1:143: NODEID
				{
				mNODEID(); 

				}
				break;
			case 22 :
				// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:1:150: PNAME_NS
				{
				mPNAME_NS(); 

				}
				break;
			case 23 :
				// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:1:159: WS
				{
				mWS(); 

				}
				break;

		}
	}


	protected DFA12 dfa12 = new DFA12(this);
	static final String DFA12_eotS =
		"\21\uffff\1\34\3\uffff\1\41\1\43\2\45\2\uffff\1\45\10\uffff\1\45\1\uffff"+
		"\1\45\1\uffff\2\45\1\uffff\2\45\1\uffff\1\45\1\62\1\uffff\1\45\2\uffff"+
		"\1\45\1\uffff\1\45\1\uffff\1\73\3\uffff";
	static final String DFA12_eofS =
		"\74\uffff";
	static final String DFA12_minS =
		"\1\11\3\uffff\3\40\1\uffff\2\55\3\uffff\1\55\2\uffff\1\55\1\60\2\uffff"+
		"\1\117\2\120\5\55\2\uffff\1\156\5\uffff\1\55\1\uffff\1\55\1\164\2\55\1"+
		"\157\2\55\1\154\2\55\1\157\1\55\1\uffff\1\147\1\55\1\171\1\55\1\106\1"+
		"\55\3\uffff";
	static final String DFA12_maxS =
		"\1\ufffd\3\uffff\3\40\1\uffff\2\ufffd\3\uffff\1\ufffd\2\uffff\2\ufffd"+
		"\2\uffff\3\120\5\ufffd\2\uffff\1\156\5\uffff\1\ufffd\1\uffff\1\ufffd\1"+
		"\164\2\ufffd\1\157\2\ufffd\1\154\2\ufffd\1\157\1\ufffd\1\uffff\1\147\1"+
		"\ufffd\1\171\1\ufffd\1\111\1\ufffd\3\uffff";
	static final String DFA12_acceptS =
		"\1\uffff\1\1\1\2\1\3\3\uffff\1\13\2\uffff\1\16\1\17\1\20\1\uffff\1\22"+
		"\1\23\2\uffff\1\25\1\27\10\uffff\1\26\1\24\1\uffff\1\6\1\10\1\7\1\12\1"+
		"\11\1\uffff\1\21\14\uffff\1\15\6\uffff\1\4\1\5\1\14";
	static final String DFA12_specialS =
		"\74\uffff}>";
	static final String[] DFA12_transitionS = {
			"\2\23\1\uffff\2\23\22\uffff\1\23\1\uffff\1\16\1\1\4\uffff\1\2\1\3\1\4"+
			"\1\5\1\uffff\1\6\2\uffff\12\14\1\21\1\uffff\1\17\1\7\2\uffff\1\13\1\10"+
			"\7\15\1\11\21\15\3\uffff\1\12\1\22\1\uffff\32\20\105\uffff\27\20\1\uffff"+
			"\37\20\1\uffff\u0208\20\160\uffff\16\20\1\uffff\u1c81\20\14\uffff\2\20"+
			"\142\uffff\u0120\20\u0a70\uffff\u03f0\20\21\uffff\ua7ff\20\u2100\uffff"+
			"\u04d0\20\40\uffff\u020e\20",
			"",
			"",
			"",
			"\1\24",
			"\1\25",
			"\1\26",
			"",
			"\1\31\1\32\1\uffff\12\31\1\21\6\uffff\32\30\4\uffff\1\31\1\uffff\15"+
			"\30\1\27\14\30\74\uffff\1\31\10\uffff\27\31\1\uffff\37\31\1\uffff\u0286"+
			"\31\1\uffff\u1c81\31\14\uffff\2\31\61\uffff\2\31\57\uffff\u0120\31\u0a70"+
			"\uffff\u03f0\31\21\uffff\ua7ff\31\u2100\uffff\u04d0\31\40\uffff\u020e"+
			"\31",
			"\1\31\1\32\1\uffff\12\31\1\21\6\uffff\32\30\4\uffff\1\31\1\uffff\14"+
			"\30\1\33\15\30\74\uffff\1\31\10\uffff\27\31\1\uffff\37\31\1\uffff\u0286"+
			"\31\1\uffff\u1c81\31\14\uffff\2\31\61\uffff\2\31\57\uffff\u0120\31\u0a70"+
			"\uffff\u03f0\31\21\uffff\ua7ff\31\u2100\uffff\u04d0\31\40\uffff\u020e"+
			"\31",
			"",
			"",
			"",
			"\1\31\1\32\1\uffff\12\31\1\21\6\uffff\32\30\4\uffff\1\31\1\uffff\32"+
			"\30\74\uffff\1\31\10\uffff\27\31\1\uffff\37\31\1\uffff\u0286\31\1\uffff"+
			"\u1c81\31\14\uffff\2\31\61\uffff\2\31\57\uffff\u0120\31\u0a70\uffff\u03f0"+
			"\31\21\uffff\ua7ff\31\u2100\uffff\u04d0\31\40\uffff\u020e\31",
			"",
			"",
			"\1\31\1\32\1\uffff\12\31\1\21\6\uffff\32\31\4\uffff\1\31\1\uffff\32"+
			"\31\74\uffff\1\31\10\uffff\27\31\1\uffff\37\31\1\uffff\u0286\31\1\uffff"+
			"\u1c81\31\14\uffff\2\31\61\uffff\2\31\57\uffff\u0120\31\u0a70\uffff\u03f0"+
			"\31\21\uffff\ua7ff\31\u2100\uffff\u04d0\31\40\uffff\u020e\31",
			"\12\35\7\uffff\32\35\4\uffff\1\35\1\uffff\32\35\105\uffff\27\35\1\uffff"+
			"\37\35\1\uffff\u0208\35\160\uffff\16\35\1\uffff\u1c81\35\14\uffff\2\35"+
			"\142\uffff\u0120\35\u0a70\uffff\u03f0\35\21\uffff\ua7ff\35\u2100\uffff"+
			"\u04d0\35\40\uffff\u020e\35",
			"",
			"",
			"\1\36\1\37",
			"\1\40",
			"\1\42",
			"\1\31\1\32\1\uffff\12\31\1\21\6\uffff\32\30\4\uffff\1\31\1\uffff\15"+
			"\30\1\44\14\30\74\uffff\1\31\10\uffff\27\31\1\uffff\37\31\1\uffff\u0286"+
			"\31\1\uffff\u1c81\31\14\uffff\2\31\61\uffff\2\31\57\uffff\u0120\31\u0a70"+
			"\uffff\u03f0\31\21\uffff\ua7ff\31\u2100\uffff\u04d0\31\40\uffff\u020e"+
			"\31",
			"\1\31\1\32\1\uffff\12\31\1\21\6\uffff\32\30\4\uffff\1\31\1\uffff\32"+
			"\30\74\uffff\1\31\10\uffff\27\31\1\uffff\37\31\1\uffff\u0286\31\1\uffff"+
			"\u1c81\31\14\uffff\2\31\61\uffff\2\31\57\uffff\u0120\31\u0a70\uffff\u03f0"+
			"\31\21\uffff\ua7ff\31\u2100\uffff\u04d0\31\40\uffff\u020e\31",
			"\1\31\1\32\1\uffff\12\31\1\21\6\uffff\32\31\4\uffff\1\31\1\uffff\32"+
			"\31\74\uffff\1\31\10\uffff\27\31\1\uffff\37\31\1\uffff\u0286\31\1\uffff"+
			"\u1c81\31\14\uffff\2\31\61\uffff\2\31\57\uffff\u0120\31\u0a70\uffff\u03f0"+
			"\31\21\uffff\ua7ff\31\u2100\uffff\u04d0\31\40\uffff\u020e\31",
			"\1\31\1\32\1\uffff\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31\74"+
			"\uffff\1\31\10\uffff\27\31\1\uffff\37\31\1\uffff\u0286\31\1\uffff\u1c81"+
			"\31\14\uffff\2\31\61\uffff\2\31\57\uffff\u0120\31\u0a70\uffff\u03f0\31"+
			"\21\uffff\ua7ff\31\u2100\uffff\u04d0\31\40\uffff\u020e\31",
			"\1\31\1\32\1\uffff\12\31\1\21\6\uffff\32\30\4\uffff\1\31\1\uffff\17"+
			"\30\1\46\12\30\74\uffff\1\31\10\uffff\27\31\1\uffff\37\31\1\uffff\u0286"+
			"\31\1\uffff\u1c81\31\14\uffff\2\31\61\uffff\2\31\57\uffff\u0120\31\u0a70"+
			"\uffff\u03f0\31\21\uffff\ua7ff\31\u2100\uffff\u04d0\31\40\uffff\u020e"+
			"\31",
			"",
			"",
			"\1\47",
			"",
			"",
			"",
			"",
			"",
			"\1\31\1\32\1\uffff\12\31\1\21\6\uffff\32\30\4\uffff\1\31\1\uffff\16"+
			"\30\1\50\13\30\74\uffff\1\31\10\uffff\27\31\1\uffff\37\31\1\uffff\u0286"+
			"\31\1\uffff\u1c81\31\14\uffff\2\31\61\uffff\2\31\57\uffff\u0120\31\u0a70"+
			"\uffff\u03f0\31\21\uffff\ua7ff\31\u2100\uffff\u04d0\31\40\uffff\u020e"+
			"\31",
			"",
			"\1\31\1\32\1\uffff\12\31\1\21\6\uffff\32\30\4\uffff\1\31\1\uffff\16"+
			"\30\1\51\13\30\74\uffff\1\31\10\uffff\27\31\1\uffff\37\31\1\uffff\u0286"+
			"\31\1\uffff\u1c81\31\14\uffff\2\31\61\uffff\2\31\57\uffff\u0120\31\u0a70"+
			"\uffff\u03f0\31\21\uffff\ua7ff\31\u2100\uffff\u04d0\31\40\uffff\u020e"+
			"\31",
			"\1\52",
			"\1\31\1\32\1\uffff\12\31\1\21\6\uffff\32\30\4\uffff\1\31\1\uffff\23"+
			"\30\1\53\6\30\74\uffff\1\31\10\uffff\27\31\1\uffff\37\31\1\uffff\u0286"+
			"\31\1\uffff\u1c81\31\14\uffff\2\31\61\uffff\2\31\57\uffff\u0120\31\u0a70"+
			"\uffff\u03f0\31\21\uffff\ua7ff\31\u2100\uffff\u04d0\31\40\uffff\u020e"+
			"\31",
			"\1\31\1\32\1\uffff\12\31\1\21\6\uffff\32\30\4\uffff\1\31\1\uffff\21"+
			"\30\1\54\10\30\74\uffff\1\31\10\uffff\27\31\1\uffff\37\31\1\uffff\u0286"+
			"\31\1\uffff\u1c81\31\14\uffff\2\31\61\uffff\2\31\57\uffff\u0120\31\u0a70"+
			"\uffff\u03f0\31\21\uffff\ua7ff\31\u2100\uffff\u04d0\31\40\uffff\u020e"+
			"\31",
			"\1\55",
			"\1\31\1\32\1\uffff\12\31\1\21\6\uffff\32\30\4\uffff\1\31\1\uffff\1\56"+
			"\31\30\74\uffff\1\31\10\uffff\27\31\1\uffff\37\31\1\uffff\u0286\31\1"+
			"\uffff\u1c81\31\14\uffff\2\31\61\uffff\2\31\57\uffff\u0120\31\u0a70\uffff"+
			"\u03f0\31\21\uffff\ua7ff\31\u2100\uffff\u04d0\31\40\uffff\u020e\31",
			"\1\31\1\32\1\uffff\12\31\1\21\6\uffff\32\30\4\uffff\1\31\1\uffff\23"+
			"\30\1\57\6\30\74\uffff\1\31\10\uffff\27\31\1\uffff\37\31\1\uffff\u0286"+
			"\31\1\uffff\u1c81\31\14\uffff\2\31\61\uffff\2\31\57\uffff\u0120\31\u0a70"+
			"\uffff\u03f0\31\21\uffff\ua7ff\31\u2100\uffff\u04d0\31\40\uffff\u020e"+
			"\31",
			"\1\60",
			"\1\31\1\32\1\uffff\12\31\1\21\6\uffff\32\30\4\uffff\1\31\1\uffff\23"+
			"\30\1\61\6\30\74\uffff\1\31\10\uffff\27\31\1\uffff\37\31\1\uffff\u0286"+
			"\31\1\uffff\u1c81\31\14\uffff\2\31\61\uffff\2\31\57\uffff\u0120\31\u0a70"+
			"\uffff\u03f0\31\21\uffff\ua7ff\31\u2100\uffff\u04d0\31\40\uffff\u020e"+
			"\31",
			"\1\31\1\32\1\uffff\12\31\1\21\6\uffff\32\30\4\uffff\1\31\1\uffff\32"+
			"\30\74\uffff\1\31\10\uffff\27\31\1\uffff\37\31\1\uffff\u0286\31\1\uffff"+
			"\u1c81\31\14\uffff\2\31\61\uffff\2\31\57\uffff\u0120\31\u0a70\uffff\u03f0"+
			"\31\21\uffff\ua7ff\31\u2100\uffff\u04d0\31\40\uffff\u020e\31",
			"\1\63",
			"\1\31\1\32\1\uffff\12\31\1\21\6\uffff\32\30\4\uffff\1\31\1\uffff\10"+
			"\30\1\64\21\30\74\uffff\1\31\10\uffff\27\31\1\uffff\37\31\1\uffff\u0286"+
			"\31\1\uffff\u1c81\31\14\uffff\2\31\61\uffff\2\31\57\uffff\u0120\31\u0a70"+
			"\uffff\u03f0\31\21\uffff\ua7ff\31\u2100\uffff\u04d0\31\40\uffff\u020e"+
			"\31",
			"",
			"\1\65",
			"\1\31\1\32\1\uffff\12\31\1\21\6\uffff\32\30\4\uffff\1\31\1\uffff\16"+
			"\30\1\66\13\30\74\uffff\1\31\10\uffff\27\31\1\uffff\37\31\1\uffff\u0286"+
			"\31\1\uffff\u1c81\31\14\uffff\2\31\61\uffff\2\31\57\uffff\u0120\31\u0a70"+
			"\uffff\u03f0\31\21\uffff\ua7ff\31\u2100\uffff\u04d0\31\40\uffff\u020e"+
			"\31",
			"\1\67",
			"\1\31\1\32\1\uffff\12\31\1\21\6\uffff\32\30\4\uffff\1\31\1\uffff\15"+
			"\30\1\70\14\30\74\uffff\1\31\10\uffff\27\31\1\uffff\37\31\1\uffff\u0286"+
			"\31\1\uffff\u1c81\31\14\uffff\2\31\61\uffff\2\31\57\uffff\u0120\31\u0a70"+
			"\uffff\u03f0\31\21\uffff\ua7ff\31\u2100\uffff\u04d0\31\40\uffff\u020e"+
			"\31",
			"\1\71\2\uffff\1\72",
			"\1\31\1\32\1\uffff\12\31\1\21\6\uffff\32\30\4\uffff\1\31\1\uffff\32"+
			"\30\74\uffff\1\31\10\uffff\27\31\1\uffff\37\31\1\uffff\u0286\31\1\uffff"+
			"\u1c81\31\14\uffff\2\31\61\uffff\2\31\57\uffff\u0120\31\u0a70\uffff\u03f0"+
			"\31\21\uffff\ua7ff\31\u2100\uffff\u04d0\31\40\uffff\u020e\31",
			"",
			"",
			""
	};

	static final short[] DFA12_eot = DFA.unpackEncodedString(DFA12_eotS);
	static final short[] DFA12_eof = DFA.unpackEncodedString(DFA12_eofS);
	static final char[] DFA12_min = DFA.unpackEncodedStringToUnsignedChars(DFA12_minS);
	static final char[] DFA12_max = DFA.unpackEncodedStringToUnsignedChars(DFA12_maxS);
	static final short[] DFA12_accept = DFA.unpackEncodedString(DFA12_acceptS);
	static final short[] DFA12_special = DFA.unpackEncodedString(DFA12_specialS);
	static final short[][] DFA12_transition;

	static {
		int numStates = DFA12_transitionS.length;
		DFA12_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA12_transition[i] = DFA.unpackEncodedString(DFA12_transitionS[i]);
		}
	}

	class DFA12 extends DFA {

		public DFA12(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 12;
			this.eot = DFA12_eot;
			this.eof = DFA12_eof;
			this.min = DFA12_min;
			this.max = DFA12_max;
			this.accept = DFA12_accept;
			this.special = DFA12_special;
			this.transition = DFA12_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | LANG_TAG | INT | NAME | QUOTED_STRING | FULLIRI | PNAME_LN | NODEID | PNAME_NS | WS );";
		}
	}

}
