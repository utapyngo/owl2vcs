// $ANTLR 3.5-rc-1 R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g 2012-12-24 22:41:40

package owl2vcs.io;

import java.io.IOException;
import java.io.StringReader;

import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntologyLoaderConfiguration;

import org.semanticweb.owlapi.change.OWLOntologyChangeData;
import org.semanticweb.owlapi.change.SetOntologyIDData;
import org.semanticweb.owlapi.model.OWLOntologyID;
import org.semanticweb.owlapi.change.AddOntologyAnnotationData;
import org.semanticweb.owlapi.change.RemoveOntologyAnnotationData;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.change.ImportChangeData;
import org.semanticweb.owlapi.change.AddImportData;
import org.semanticweb.owlapi.change.RemoveImportData;
import org.semanticweb.owlapi.model.OWLImportsDeclaration;
import org.semanticweb.owlapi.change.AxiomChangeData;
import org.semanticweb.owlapi.change.AddAxiomData;
import org.semanticweb.owlapi.change.RemoveAxiomData;
import org.semanticweb.owlapi.model.OWLAxiom;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import org.semanticweb.owlapi.apibinding.OWLManager;

import org.coode.owlapi.functionalparser.OWLFunctionalSyntaxParser;
import org.coode.owlapi.functionalparser.ParseException;

import org.semanticweb.owlapi.util.DefaultPrefixManager;

import owl2vcs.changeset.MutableChangeSet;
import owl2vcs.changes.CustomOntologyChangeData;
import owl2vcs.changes.SetOntologyFormatData;
import owl2vcs.changes.PrefixChangeData;
import owl2vcs.changes.AddPrefixData;
import owl2vcs.changes.RemovePrefixData;
import owl2vcs.changes.ModifyPrefixData;
import owl2vcs.changes.RenamePrefixData;
import owl2vcs.changes.UnknownOntologyFormatException;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class FunctionalChangesetParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "FULLIRI", "INT", "LANG_TAG", "NAME", "NODEID", "PNAME_LN", "PNAME_NS", "PN_CHARS", "PN_CHARS_BASE", "PN_CHARS_U", "PN_LOCAL", "PN_PREFIX", "QUOTED_STRING", "WS", "'# Prefix'", "'('", "')'", "'* '", "'* OntologyFormat'", "'* Prefix'", "'+ '", "'+ Prefix'", "'- '", "'- Prefix'", "'='", "'Annotation'", "'Import'", "'OntologyID'", "'^^'"
	};

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
	public static final int T__32=32;
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
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public FunctionalChangesetParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public FunctionalChangesetParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return FunctionalChangesetParser.tokenNames; }
	@Override public String getGrammarFileName() { return "R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g"; }


	private OWLOntologyManager manager;
	private OWLDataFactory dataFactory;
	private OWLOntology ontology;
	private OWLFunctionalSyntaxParser parser;

	public void init() {
	    manager = OWLManager.createOWLOntologyManager();
	    dataFactory = manager.getOWLDataFactory();
	    try {
	        ontology = manager.createOntology();
	    } catch (OWLOntologyCreationException e) {
	        e.printStackTrace(System.err);
	    }
	    parser = new OWLFunctionalSyntaxParser(new StringReader(""));
	    parser.setUp(ontology, new OWLOntologyLoaderConfiguration());
	}

	public FunctionalChangesetParser(String fileName)
	        throws OWLOntologyCreationException, IOException {
	    this(new CommonTokenStream(new FunctionalChangesetLexer(
	        new ANTLRFileStream(fileName, "UTF-8"))));
	}

	public OWLAnnotation parseAnnotation(String s)
	        throws ParseException {
	    parser.ReInit(new StringReader(s));
	    OWLAnnotation a = null;
	    // try {
	        a = parser.Annotation();
	    // } catch (Exception e) {
	    //     System.err.println("Error parsing annotation: " + s);
	    //     throw e;
	    // }
	    return a;
	}

	public OWLAxiom parseAxiom(String s)
	        throws ParseException {
	    parser.ReInit(new StringReader(s));
	    OWLAxiom a = null;
	    // try {
	        a = parser.Axiom();
	    // } catch (Exception e) {
	    //     System.err.println("Error parsing axiom: " + s);
	    //     throw e;
	    // }
	    return a;
	}




	// $ANTLR start "changeset"
	// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:113:1: changeset returns [MutableChangeSet result] : prefixes ( setOntologyFormat )? ( setOntologyId )? ( prefixChange )* ( importChange )* ( annotationChange )* ( axiomChange )* ;
	public final MutableChangeSet changeset() throws RecognitionException, UnknownOntologyFormatException, ParseException {
		MutableChangeSet result = null;


		DefaultPrefixManager prefixes1 =null;
		SetOntologyFormatData setOntologyFormat2 =null;
		SetOntologyIDData setOntologyId3 =null;
		PrefixChangeData prefixChange4 =null;
		ImportChangeData importChange5 =null;
		OWLOntologyChangeData annotationChange6 =null;
		AxiomChangeData axiomChange7 =null;

		try {
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:115:3: ( prefixes ( setOntologyFormat )? ( setOntologyId )? ( prefixChange )* ( importChange )* ( annotationChange )* ( axiomChange )* )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:116:3: prefixes ( setOntologyFormat )? ( setOntologyId )? ( prefixChange )* ( importChange )* ( annotationChange )* ( axiomChange )*
			{

			    result = new MutableChangeSet();
			  
			pushFollow(FOLLOW_prefixes_in_changeset75);
			prefixes1=prefixes();
			state._fsp--;

			 parser.setPrefixes(prefixes1); 
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:121:3: ( setOntologyFormat )?
			int alt1=2;
			int LA1_0 = input.LA(1);
			if ( (LA1_0==22) ) {
				alt1=1;
			}
			switch (alt1) {
				case 1 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:121:4: setOntologyFormat
					{
					pushFollow(FOLLOW_setOntologyFormat_in_changeset86);
					setOntologyFormat2=setOntologyFormat();
					state._fsp--;

					 result.setFormatChange(setOntologyFormat2); 
					}
					break;

			}

			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:124:3: ( setOntologyId )?
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0==21) ) {
				alt2=1;
			}
			switch (alt2) {
				case 1 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:124:4: setOntologyId
					{
					pushFollow(FOLLOW_setOntologyId_in_changeset102);
					setOntologyId3=setOntologyId();
					state._fsp--;

					 result.setOntologyIdChange(setOntologyId3); 
					}
					break;

			}

			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:127:3: ( prefixChange )*
			loop3:
			do {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( (LA3_0==18||LA3_0==23||LA3_0==25||LA3_0==27) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:127:4: prefixChange
					{
					pushFollow(FOLLOW_prefixChange_in_changeset118);
					prefixChange4=prefixChange();
					state._fsp--;

					 result.addPrefixChange(prefixChange4); 
					}
					break;

				default :
					break loop3;
				}
			} while (true);

			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:130:3: ( importChange )*
			loop4:
			do {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( (LA4_0==24) ) {
					int LA4_1 = input.LA(2);
					if ( (LA4_1==30) ) {
						alt4=1;
					}

				}
				else if ( (LA4_0==26) ) {
					int LA4_2 = input.LA(2);
					if ( (LA4_2==30) ) {
						alt4=1;
					}

				}

				switch (alt4) {
				case 1 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:130:4: importChange
					{
					pushFollow(FOLLOW_importChange_in_changeset134);
					importChange5=importChange();
					state._fsp--;

					 result.addImportChange(importChange5); 
					}
					break;

				default :
					break loop4;
				}
			} while (true);

			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:133:3: ( annotationChange )*
			loop5:
			do {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( (LA5_0==24) ) {
					int LA5_1 = input.LA(2);
					if ( (LA5_1==29) ) {
						alt5=1;
					}

				}
				else if ( (LA5_0==26) ) {
					int LA5_2 = input.LA(2);
					if ( (LA5_2==29) ) {
						alt5=1;
					}

				}

				switch (alt5) {
				case 1 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:133:4: annotationChange
					{
					pushFollow(FOLLOW_annotationChange_in_changeset150);
					annotationChange6=annotationChange();
					state._fsp--;

					 result.addAnnotationChange(annotationChange6); 
					}
					break;

				default :
					break loop5;
				}
			} while (true);

			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:136:3: ( axiomChange )*
			loop6:
			do {
				int alt6=2;
				int LA6_0 = input.LA(1);
				if ( (LA6_0==24||LA6_0==26) ) {
					alt6=1;
				}

				switch (alt6) {
				case 1 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:136:4: axiomChange
					{
					pushFollow(FOLLOW_axiomChange_in_changeset166);
					axiomChange7=axiomChange();
					state._fsp--;

					 result.addAxiomChange(axiomChange7); 
					}
					break;

				default :
					break loop6;
				}
			} while (true);

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return result;
	}
	// $ANTLR end "changeset"



	// $ANTLR start "prefixes"
	// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:141:1: prefixes returns [DefaultPrefixManager result] : ( prefix )* ;
	public final DefaultPrefixManager prefixes() throws RecognitionException {
		DefaultPrefixManager result = null;


		ParserRuleReturnScope prefix8 =null;

		try {
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:142:3: ( ( prefix )* )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:142:5: ( prefix )*
			{
			 result = new DefaultPrefixManager(); 
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:143:3: ( prefix )*
			loop7:
			do {
				int alt7=2;
				int LA7_0 = input.LA(1);
				if ( (LA7_0==PNAME_NS) ) {
					alt7=1;
				}

				switch (alt7) {
				case 1 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:143:5: prefix
					{
					pushFollow(FOLLOW_prefix_in_prefixes200);
					prefix8=prefix();
					state._fsp--;

					 result.setPrefix((prefix8!=null?((FunctionalChangesetParser.prefix_return)prefix8).name:null), (prefix8!=null?((FunctionalChangesetParser.prefix_return)prefix8).value:null)); 
					}
					break;

				default :
					break loop7;
				}
			} while (true);

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return result;
	}
	// $ANTLR end "prefixes"


	public static class prefix_return extends ParserRuleReturnScope {
		public String name;
		public String value;
	};


	// $ANTLR start "prefix"
	// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:148:1: prefix returns [String name, String value] : prefixName '=' fullIRI ;
	public final FunctionalChangesetParser.prefix_return prefix() throws RecognitionException {
		FunctionalChangesetParser.prefix_return retval = new FunctionalChangesetParser.prefix_return();
		retval.start = input.LT(1);

		ParserRuleReturnScope prefixName9 =null;
		ParserRuleReturnScope fullIRI10 =null;

		try {
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:149:3: ( prefixName '=' fullIRI )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:149:5: prefixName '=' fullIRI
			{
			pushFollow(FOLLOW_prefixName_in_prefix228);
			prefixName9=prefixName();
			state._fsp--;

			match(input,28,FOLLOW_28_in_prefix230); 
			pushFollow(FOLLOW_fullIRI_in_prefix232);
			fullIRI10=fullIRI();
			state._fsp--;


			      retval.name = (prefixName9!=null?input.toString(prefixName9.start,prefixName9.stop):null);
			      String s = (fullIRI10!=null?input.toString(fullIRI10.start,fullIRI10.stop):null);
			      retval.value =  s.substring(1, s.length() - 1);
			  
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "prefix"



	// $ANTLR start "setOntologyFormat"
	// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:160:1: setOntologyFormat returns [SetOntologyFormatData result] : '* OntologyFormat' '(' quotedString ')' ;
	public final SetOntologyFormatData setOntologyFormat() throws RecognitionException, UnknownOntologyFormatException {
		SetOntologyFormatData result = null;


		ParserRuleReturnScope quotedString11 =null;

		try {
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:163:3: ( '* OntologyFormat' '(' quotedString ')' )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:164:3: '* OntologyFormat' '(' quotedString ')'
			{
			match(input,22,FOLLOW_22_in_setOntologyFormat266); 
			match(input,19,FOLLOW_19_in_setOntologyFormat268); 
			pushFollow(FOLLOW_quotedString_in_setOntologyFormat270);
			quotedString11=quotedString();
			state._fsp--;

			match(input,20,FOLLOW_20_in_setOntologyFormat272); 

			      String format = (quotedString11!=null?input.toString(quotedString11.start,quotedString11.stop):null).substring(1, (quotedString11!=null?input.toString(quotedString11.start,quotedString11.stop):null).length() - 1);
			      result = new SetOntologyFormatData(format);
			  
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return result;
	}
	// $ANTLR end "setOntologyFormat"



	// $ANTLR start "setOntologyId"
	// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:174:1: setOntologyId returns [SetOntologyIDData result] : '* ' ontologyIDStatement ;
	public final SetOntologyIDData setOntologyId() throws RecognitionException {
		SetOntologyIDData result = null;


		OWLOntologyID ontologyIDStatement12 =null;

		try {
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:175:3: ( '* ' ontologyIDStatement )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:175:5: '* ' ontologyIDStatement
			{
			match(input,21,FOLLOW_21_in_setOntologyId296); 
			pushFollow(FOLLOW_ontologyIDStatement_in_setOntologyId298);
			ontologyIDStatement12=ontologyIDStatement();
			state._fsp--;

			 result = new SetOntologyIDData(ontologyIDStatement12); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return result;
	}
	// $ANTLR end "setOntologyId"



	// $ANTLR start "ontologyIDStatement"
	// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:179:1: ontologyIDStatement returns [OWLOntologyID result] : ( 'OntologyID' '(' oid ')' | 'OntologyID' '(' ')' );
	public final OWLOntologyID ontologyIDStatement() throws RecognitionException {
		OWLOntologyID result = null;


		OWLOntologyID oid13 =null;

		try {
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:180:3: ( 'OntologyID' '(' oid ')' | 'OntologyID' '(' ')' )
			int alt8=2;
			int LA8_0 = input.LA(1);
			if ( (LA8_0==31) ) {
				int LA8_1 = input.LA(2);
				if ( (LA8_1==19) ) {
					int LA8_2 = input.LA(3);
					if ( (LA8_2==20) ) {
						alt8=2;
					}
					else if ( (LA8_2==FULLIRI) ) {
						alt8=1;
					}
					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++)
								input.consume();
							NoViableAltException nvae =
								new NoViableAltException("", 8, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
				}
				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 8, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
			}
			else {
				NoViableAltException nvae =
					new NoViableAltException("", 8, 0, input);
				throw nvae;
			}
			switch (alt8) {
				case 1 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:180:5: 'OntologyID' '(' oid ')'
					{
					match(input,31,FOLLOW_31_in_ontologyIDStatement319); 
					match(input,19,FOLLOW_19_in_ontologyIDStatement321); 
					pushFollow(FOLLOW_oid_in_ontologyIDStatement323);
					oid13=oid();
					state._fsp--;

					match(input,20,FOLLOW_20_in_ontologyIDStatement325); 
					 result = oid13; 
					}
					break;
				case 2 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:181:5: 'OntologyID' '(' ')'
					{
					match(input,31,FOLLOW_31_in_ontologyIDStatement333); 
					match(input,19,FOLLOW_19_in_ontologyIDStatement335); 
					match(input,20,FOLLOW_20_in_ontologyIDStatement337); 
					 result = new OWLOntologyID(); 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return result;
	}
	// $ANTLR end "ontologyIDStatement"



	// $ANTLR start "oid"
	// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:184:1: oid returns [OWLOntologyID result] : (oiri= fullIRI viri= fullIRI |oiri= fullIRI );
	public final OWLOntologyID oid() throws RecognitionException {
		OWLOntologyID result = null;


		ParserRuleReturnScope oiri =null;
		ParserRuleReturnScope viri =null;

		try {
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:185:3: (oiri= fullIRI viri= fullIRI |oiri= fullIRI )
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0==FULLIRI) ) {
				int LA9_1 = input.LA(2);
				if ( (LA9_1==FULLIRI) ) {
					alt9=1;
				}
				else if ( (LA9_1==20) ) {
					alt9=2;
				}
				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 9, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
			}
			else {
				NoViableAltException nvae =
					new NoViableAltException("", 9, 0, input);
				throw nvae;
			}
			switch (alt9) {
				case 1 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:185:5: oiri= fullIRI viri= fullIRI
					{
					pushFollow(FOLLOW_fullIRI_in_oid358);
					oiri=fullIRI();
					state._fsp--;

					pushFollow(FOLLOW_fullIRI_in_oid362);
					viri=fullIRI();
					state._fsp--;

					 result = new OWLOntologyID(parser.getIRI((oiri!=null?input.toString(oiri.start,oiri.stop):null)), parser.getIRI((viri!=null?input.toString(viri.start,viri.stop):null))); 
					}
					break;
				case 2 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:187:5: oiri= fullIRI
					{
					pushFollow(FOLLOW_fullIRI_in_oid374);
					oiri=fullIRI();
					state._fsp--;

					 result = new OWLOntologyID(parser.getIRI((oiri!=null?input.toString(oiri.start,oiri.stop):null))); 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return result;
	}
	// $ANTLR end "oid"



	// $ANTLR start "prefixChange"
	// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:194:1: prefixChange returns [PrefixChangeData result] : ( '+ Prefix' '(' prefix ')' | '- Prefix' '(' prefix ')' | '* Prefix' '(' prefix newvalue= fullIRI ')' | '# Prefix' '(' oldname= prefixName prefix ')' );
	public final PrefixChangeData prefixChange() throws RecognitionException {
		PrefixChangeData result = null;


		ParserRuleReturnScope newvalue =null;
		ParserRuleReturnScope oldname =null;
		ParserRuleReturnScope prefix14 =null;
		ParserRuleReturnScope prefix15 =null;
		ParserRuleReturnScope prefix16 =null;
		ParserRuleReturnScope prefix17 =null;

		try {
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:195:3: ( '+ Prefix' '(' prefix ')' | '- Prefix' '(' prefix ')' | '* Prefix' '(' prefix newvalue= fullIRI ')' | '# Prefix' '(' oldname= prefixName prefix ')' )
			int alt10=4;
			switch ( input.LA(1) ) {
			case 25:
				{
				alt10=1;
				}
				break;
			case 27:
				{
				alt10=2;
				}
				break;
			case 23:
				{
				alt10=3;
				}
				break;
			case 18:
				{
				alt10=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 10, 0, input);
				throw nvae;
			}
			switch (alt10) {
				case 1 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:195:5: '+ Prefix' '(' prefix ')'
					{
					match(input,25,FOLLOW_25_in_prefixChange398); 
					match(input,19,FOLLOW_19_in_prefixChange400); 
					pushFollow(FOLLOW_prefix_in_prefixChange402);
					prefix14=prefix();
					state._fsp--;

					match(input,20,FOLLOW_20_in_prefixChange404); 
					 result = new AddPrefixData((prefix14!=null?((FunctionalChangesetParser.prefix_return)prefix14).name:null), (prefix14!=null?((FunctionalChangesetParser.prefix_return)prefix14).value:null)); 
					}
					break;
				case 2 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:197:5: '- Prefix' '(' prefix ')'
					{
					match(input,27,FOLLOW_27_in_prefixChange414); 
					match(input,19,FOLLOW_19_in_prefixChange416); 
					pushFollow(FOLLOW_prefix_in_prefixChange418);
					prefix15=prefix();
					state._fsp--;

					match(input,20,FOLLOW_20_in_prefixChange420); 
					 result = new RemovePrefixData((prefix15!=null?((FunctionalChangesetParser.prefix_return)prefix15).name:null), (prefix15!=null?((FunctionalChangesetParser.prefix_return)prefix15).value:null)); 
					}
					break;
				case 3 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:199:5: '* Prefix' '(' prefix newvalue= fullIRI ')'
					{
					match(input,23,FOLLOW_23_in_prefixChange430); 
					match(input,19,FOLLOW_19_in_prefixChange432); 
					pushFollow(FOLLOW_prefix_in_prefixChange434);
					prefix16=prefix();
					state._fsp--;

					pushFollow(FOLLOW_fullIRI_in_prefixChange438);
					newvalue=fullIRI();
					state._fsp--;

					match(input,20,FOLLOW_20_in_prefixChange440); 
					 result = new ModifyPrefixData( (prefix16!=null?((FunctionalChangesetParser.prefix_return)prefix16).name:null), (prefix16!=null?((FunctionalChangesetParser.prefix_return)prefix16).value:null), (newvalue!=null?input.toString(newvalue.start,newvalue.stop):null)); 
					}
					break;
				case 4 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:201:5: '# Prefix' '(' oldname= prefixName prefix ')'
					{
					match(input,18,FOLLOW_18_in_prefixChange450); 
					match(input,19,FOLLOW_19_in_prefixChange452); 
					pushFollow(FOLLOW_prefixName_in_prefixChange456);
					oldname=prefixName();
					state._fsp--;

					pushFollow(FOLLOW_prefix_in_prefixChange458);
					prefix17=prefix();
					state._fsp--;

					match(input,20,FOLLOW_20_in_prefixChange460); 
					 result = new RenamePrefixData((oldname!=null?input.toString(oldname.start,oldname.stop):null), (prefix17!=null?((FunctionalChangesetParser.prefix_return)prefix17).value:null), (prefix17!=null?((FunctionalChangesetParser.prefix_return)prefix17).name:null)); 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return result;
	}
	// $ANTLR end "prefixChange"



	// $ANTLR start "importChange"
	// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:208:1: importChange returns [ImportChangeData result] : ( '+ ' importsDeclaration | '- ' importsDeclaration );
	public final ImportChangeData importChange() throws RecognitionException {
		ImportChangeData result = null;


		OWLImportsDeclaration importsDeclaration18 =null;
		OWLImportsDeclaration importsDeclaration19 =null;

		try {
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:209:3: ( '+ ' importsDeclaration | '- ' importsDeclaration )
			int alt11=2;
			int LA11_0 = input.LA(1);
			if ( (LA11_0==24) ) {
				alt11=1;
			}
			else if ( (LA11_0==26) ) {
				alt11=2;
			}
			else {
				NoViableAltException nvae =
					new NoViableAltException("", 11, 0, input);
				throw nvae;
			}
			switch (alt11) {
				case 1 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:209:5: '+ ' importsDeclaration
					{
					match(input,24,FOLLOW_24_in_importChange484); 
					pushFollow(FOLLOW_importsDeclaration_in_importChange486);
					importsDeclaration18=importsDeclaration();
					state._fsp--;

					 result = new AddImportData(importsDeclaration18); 
					}
					break;
				case 2 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:211:5: '- ' importsDeclaration
					{
					match(input,26,FOLLOW_26_in_importChange496); 
					pushFollow(FOLLOW_importsDeclaration_in_importChange498);
					importsDeclaration19=importsDeclaration();
					state._fsp--;

					 result = new RemoveImportData(importsDeclaration19); 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return result;
	}
	// $ANTLR end "importChange"



	// $ANTLR start "importsDeclaration"
	// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:215:1: importsDeclaration returns [OWLImportsDeclaration result] : 'Import' '(' iri ')' ;
	public final OWLImportsDeclaration importsDeclaration() throws RecognitionException {
		OWLImportsDeclaration result = null;


		ParserRuleReturnScope iri20 =null;

		try {
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:216:3: ( 'Import' '(' iri ')' )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:216:5: 'Import' '(' iri ')'
			{
			match(input,30,FOLLOW_30_in_importsDeclaration519); 
			match(input,19,FOLLOW_19_in_importsDeclaration521); 
			pushFollow(FOLLOW_iri_in_importsDeclaration523);
			iri20=iri();
			state._fsp--;

			match(input,20,FOLLOW_20_in_importsDeclaration525); 
			 result = dataFactory.getOWLImportsDeclaration(parser.getIRI((iri20!=null?input.toString(iri20.start,iri20.stop):null))); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return result;
	}
	// $ANTLR end "importsDeclaration"



	// $ANTLR start "annotationChange"
	// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:223:1: annotationChange returns [OWLOntologyChangeData result] : ( '+ ' annotation | '- ' annotation );
	public final OWLOntologyChangeData annotationChange() throws RecognitionException, ParseException {
		OWLOntologyChangeData result = null;


		ParserRuleReturnScope annotation21 =null;
		ParserRuleReturnScope annotation22 =null;

		try {
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:225:3: ( '+ ' annotation | '- ' annotation )
			int alt12=2;
			int LA12_0 = input.LA(1);
			if ( (LA12_0==24) ) {
				alt12=1;
			}
			else if ( (LA12_0==26) ) {
				alt12=2;
			}
			else {
				NoViableAltException nvae =
					new NoViableAltException("", 12, 0, input);
				throw nvae;
			}
			switch (alt12) {
				case 1 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:225:5: '+ ' annotation
					{
					match(input,24,FOLLOW_24_in_annotationChange555); 
					pushFollow(FOLLOW_annotation_in_annotationChange557);
					annotation21=annotation();
					state._fsp--;

					 result = new AddOntologyAnnotationData((annotation21!=null?((FunctionalChangesetParser.annotation_return)annotation21).result:null)); 
					}
					break;
				case 2 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:227:5: '- ' annotation
					{
					match(input,26,FOLLOW_26_in_annotationChange567); 
					pushFollow(FOLLOW_annotation_in_annotationChange569);
					annotation22=annotation();
					state._fsp--;

					 result = new RemoveOntologyAnnotationData((annotation22!=null?((FunctionalChangesetParser.annotation_return)annotation22).result:null)); 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return result;
	}
	// $ANTLR end "annotationChange"


	public static class annotation_return extends ParserRuleReturnScope {
		public OWLAnnotation result;
	};


	// $ANTLR start "annotation"
	// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:231:1: annotation returns [OWLAnnotation result] : subAnnotation ;
	public final FunctionalChangesetParser.annotation_return annotation() throws RecognitionException, ParseException {
		FunctionalChangesetParser.annotation_return retval = new FunctionalChangesetParser.annotation_return();
		retval.start = input.LT(1);

		try {
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:233:3: ( subAnnotation )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:233:5: subAnnotation
			{
			pushFollow(FOLLOW_subAnnotation_in_annotation596);
			subAnnotation();
			state._fsp--;

			 retval.result = parseAnnotation(input.toString(retval.start,input.LT(-1))); 
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "annotation"



	// $ANTLR start "subAnnotation"
	// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:237:1: subAnnotation : 'Annotation' '(' ( subAnnotation )* iri annotationValue ')' ;
	public final void subAnnotation() throws RecognitionException {
		try {
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:238:3: ( 'Annotation' '(' ( subAnnotation )* iri annotationValue ')' )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:238:5: 'Annotation' '(' ( subAnnotation )* iri annotationValue ')'
			{
			match(input,29,FOLLOW_29_in_subAnnotation613); 
			match(input,19,FOLLOW_19_in_subAnnotation615); 
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:238:22: ( subAnnotation )*
			loop13:
			do {
				int alt13=2;
				int LA13_0 = input.LA(1);
				if ( (LA13_0==29) ) {
					alt13=1;
				}

				switch (alt13) {
				case 1 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:238:22: subAnnotation
					{
					pushFollow(FOLLOW_subAnnotation_in_subAnnotation617);
					subAnnotation();
					state._fsp--;

					}
					break;

				default :
					break loop13;
				}
			} while (true);

			pushFollow(FOLLOW_iri_in_subAnnotation620);
			iri();
			state._fsp--;

			pushFollow(FOLLOW_annotationValue_in_subAnnotation622);
			annotationValue();
			state._fsp--;

			match(input,20,FOLLOW_20_in_subAnnotation624); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return ;
	}
	// $ANTLR end "subAnnotation"



	// $ANTLR start "annotationValue"
	// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:241:1: annotationValue : ( iri | anonymousIndividual | literal );
	public final void annotationValue() throws RecognitionException {
		try {
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:242:3: ( iri | anonymousIndividual | literal )
			int alt14=3;
			switch ( input.LA(1) ) {
			case FULLIRI:
			case PNAME_LN:
				{
				alt14=1;
				}
				break;
			case NODEID:
				{
				alt14=2;
				}
				break;
			case QUOTED_STRING:
				{
				alt14=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 14, 0, input);
				throw nvae;
			}
			switch (alt14) {
				case 1 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:242:5: iri
					{
					pushFollow(FOLLOW_iri_in_annotationValue637);
					iri();
					state._fsp--;

					}
					break;
				case 2 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:243:5: anonymousIndividual
					{
					pushFollow(FOLLOW_anonymousIndividual_in_annotationValue643);
					anonymousIndividual();
					state._fsp--;

					}
					break;
				case 3 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:244:5: literal
					{
					pushFollow(FOLLOW_literal_in_annotationValue649);
					literal();
					state._fsp--;

					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return ;
	}
	// $ANTLR end "annotationValue"



	// $ANTLR start "axiomChange"
	// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:249:1: axiomChange returns [AxiomChangeData result] : ( '+ ' axiom | '- ' axiom );
	public final AxiomChangeData axiomChange() throws RecognitionException, ParseException {
		AxiomChangeData result = null;


		ParserRuleReturnScope axiom23 =null;
		ParserRuleReturnScope axiom24 =null;

		try {
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:251:3: ( '+ ' axiom | '- ' axiom )
			int alt15=2;
			int LA15_0 = input.LA(1);
			if ( (LA15_0==24) ) {
				alt15=1;
			}
			else if ( (LA15_0==26) ) {
				alt15=2;
			}
			else {
				NoViableAltException nvae =
					new NoViableAltException("", 15, 0, input);
				throw nvae;
			}
			switch (alt15) {
				case 1 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:251:5: '+ ' axiom
					{
					match(input,24,FOLLOW_24_in_axiomChange674); 
					pushFollow(FOLLOW_axiom_in_axiomChange676);
					axiom23=axiom();
					state._fsp--;

					 result = new AddAxiomData((axiom23!=null?((FunctionalChangesetParser.axiom_return)axiom23).result:null)); 
					}
					break;
				case 2 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:253:5: '- ' axiom
					{
					match(input,26,FOLLOW_26_in_axiomChange686); 
					pushFollow(FOLLOW_axiom_in_axiomChange688);
					axiom24=axiom();
					state._fsp--;

					 result = new RemoveAxiomData((axiom24!=null?((FunctionalChangesetParser.axiom_return)axiom24).result:null)); 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return result;
	}
	// $ANTLR end "axiomChange"


	public static class axiom_return extends ParserRuleReturnScope {
		public OWLAxiom result;
	};


	// $ANTLR start "axiom"
	// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:257:1: axiom returns [OWLAxiom result] : subAxiom ;
	public final FunctionalChangesetParser.axiom_return axiom() throws RecognitionException, ParseException {
		FunctionalChangesetParser.axiom_return retval = new FunctionalChangesetParser.axiom_return();
		retval.start = input.LT(1);

		try {
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:259:3: ( subAxiom )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:259:5: subAxiom
			{
			pushFollow(FOLLOW_subAxiom_in_axiom715);
			subAxiom();
			state._fsp--;

			 retval.result = parseAxiom(input.toString(retval.start,input.LT(-1))); 
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "axiom"



	// $ANTLR start "subAxiom"
	// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:263:1: subAxiom : NAME '(' ( subAnnotation )* ( INT )? ( argument )+ ')' ;
	public final void subAxiom() throws RecognitionException {
		try {
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:264:3: ( NAME '(' ( subAnnotation )* ( INT )? ( argument )+ ')' )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:264:5: NAME '(' ( subAnnotation )* ( INT )? ( argument )+ ')'
			{
			match(input,NAME,FOLLOW_NAME_in_subAxiom732); 
			match(input,19,FOLLOW_19_in_subAxiom734); 
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:264:14: ( subAnnotation )*
			loop16:
			do {
				int alt16=2;
				int LA16_0 = input.LA(1);
				if ( (LA16_0==29) ) {
					alt16=1;
				}

				switch (alt16) {
				case 1 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:264:14: subAnnotation
					{
					pushFollow(FOLLOW_subAnnotation_in_subAxiom736);
					subAnnotation();
					state._fsp--;

					}
					break;

				default :
					break loop16;
				}
			} while (true);

			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:264:29: ( INT )?
			int alt17=2;
			int LA17_0 = input.LA(1);
			if ( (LA17_0==INT) ) {
				alt17=1;
			}
			switch (alt17) {
				case 1 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:264:29: INT
					{
					match(input,INT,FOLLOW_INT_in_subAxiom739); 
					}
					break;

			}

			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:264:34: ( argument )+
			int cnt18=0;
			loop18:
			do {
				int alt18=2;
				int LA18_0 = input.LA(1);
				if ( (LA18_0==FULLIRI||(LA18_0 >= NAME && LA18_0 <= PNAME_LN)||LA18_0==QUOTED_STRING) ) {
					alt18=1;
				}

				switch (alt18) {
				case 1 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:264:34: argument
					{
					pushFollow(FOLLOW_argument_in_subAxiom742);
					argument();
					state._fsp--;

					}
					break;

				default :
					if ( cnt18 >= 1 ) break loop18;
						EarlyExitException eee =
							new EarlyExitException(18, input);
						throw eee;
				}
				cnt18++;
			} while (true);

			match(input,20,FOLLOW_20_in_subAxiom745); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return ;
	}
	// $ANTLR end "subAxiom"



	// $ANTLR start "argument"
	// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:267:1: argument : ( iri | literal | anonymousIndividual | subAxiom );
	public final void argument() throws RecognitionException {
		try {
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:268:3: ( iri | literal | anonymousIndividual | subAxiom )
			int alt19=4;
			switch ( input.LA(1) ) {
			case FULLIRI:
			case PNAME_LN:
				{
				alt19=1;
				}
				break;
			case QUOTED_STRING:
				{
				alt19=2;
				}
				break;
			case NODEID:
				{
				alt19=3;
				}
				break;
			case NAME:
				{
				alt19=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 19, 0, input);
				throw nvae;
			}
			switch (alt19) {
				case 1 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:268:5: iri
					{
					pushFollow(FOLLOW_iri_in_argument758);
					iri();
					state._fsp--;

					}
					break;
				case 2 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:269:5: literal
					{
					pushFollow(FOLLOW_literal_in_argument764);
					literal();
					state._fsp--;

					}
					break;
				case 3 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:270:5: anonymousIndividual
					{
					pushFollow(FOLLOW_anonymousIndividual_in_argument770);
					anonymousIndividual();
					state._fsp--;

					}
					break;
				case 4 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:271:5: subAxiom
					{
					pushFollow(FOLLOW_subAxiom_in_argument776);
					subAxiom();
					state._fsp--;

					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return ;
	}
	// $ANTLR end "argument"


	public static class iri_return extends ParserRuleReturnScope {
	};


	// $ANTLR start "iri"
	// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:277:1: iri : ( fullIRI | abbreviatedIRI );
	public final FunctionalChangesetParser.iri_return iri() throws RecognitionException {
		FunctionalChangesetParser.iri_return retval = new FunctionalChangesetParser.iri_return();
		retval.start = input.LT(1);

		try {
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:278:3: ( fullIRI | abbreviatedIRI )
			int alt20=2;
			int LA20_0 = input.LA(1);
			if ( (LA20_0==FULLIRI) ) {
				alt20=1;
			}
			else if ( (LA20_0==PNAME_LN) ) {
				alt20=2;
			}
			else {
				NoViableAltException nvae =
					new NoViableAltException("", 20, 0, input);
				throw nvae;
			}
			switch (alt20) {
				case 1 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:278:5: fullIRI
					{
					pushFollow(FOLLOW_fullIRI_in_iri792);
					fullIRI();
					state._fsp--;

					}
					break;
				case 2 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:279:5: abbreviatedIRI
					{
					pushFollow(FOLLOW_abbreviatedIRI_in_iri798);
					abbreviatedIRI();
					state._fsp--;

					}
					break;

			}
			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "iri"


	public static class fullIRI_return extends ParserRuleReturnScope {
	};


	// $ANTLR start "fullIRI"
	// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:282:1: fullIRI : FULLIRI ;
	public final FunctionalChangesetParser.fullIRI_return fullIRI() throws RecognitionException {
		FunctionalChangesetParser.fullIRI_return retval = new FunctionalChangesetParser.fullIRI_return();
		retval.start = input.LT(1);

		try {
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:283:3: ( FULLIRI )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:283:5: FULLIRI
			{
			match(input,FULLIRI,FOLLOW_FULLIRI_in_fullIRI811); 
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "fullIRI"



	// $ANTLR start "abbreviatedIRI"
	// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:285:1: abbreviatedIRI : PNAME_LN ;
	public final void abbreviatedIRI() throws RecognitionException {
		try {
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:286:3: ( PNAME_LN )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:286:5: PNAME_LN
			{
			match(input,PNAME_LN,FOLLOW_PNAME_LN_in_abbreviatedIRI822); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return ;
	}
	// $ANTLR end "abbreviatedIRI"


	public static class prefixName_return extends ParserRuleReturnScope {
	};


	// $ANTLR start "prefixName"
	// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:288:1: prefixName : PNAME_NS ;
	public final FunctionalChangesetParser.prefixName_return prefixName() throws RecognitionException {
		FunctionalChangesetParser.prefixName_return retval = new FunctionalChangesetParser.prefixName_return();
		retval.start = input.LT(1);

		try {
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:289:3: ( PNAME_NS )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:289:5: PNAME_NS
			{
			match(input,PNAME_NS,FOLLOW_PNAME_NS_in_prefixName833); 
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "prefixName"



	// $ANTLR start "anonymousIndividual"
	// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:291:1: anonymousIndividual : NODEID ;
	public final void anonymousIndividual() throws RecognitionException {
		try {
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:292:3: ( NODEID )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:292:5: NODEID
			{
			match(input,NODEID,FOLLOW_NODEID_in_anonymousIndividual844); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return ;
	}
	// $ANTLR end "anonymousIndividual"



	// $ANTLR start "literal"
	// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:294:1: literal : quotedString ( '^^' iri | languageTag )? ;
	public final void literal() throws RecognitionException {
		try {
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:295:3: ( quotedString ( '^^' iri | languageTag )? )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:295:5: quotedString ( '^^' iri | languageTag )?
			{
			pushFollow(FOLLOW_quotedString_in_literal855);
			quotedString();
			state._fsp--;

			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:296:5: ( '^^' iri | languageTag )?
			int alt21=3;
			int LA21_0 = input.LA(1);
			if ( (LA21_0==32) ) {
				alt21=1;
			}
			else if ( (LA21_0==LANG_TAG) ) {
				alt21=2;
			}
			switch (alt21) {
				case 1 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:296:7: '^^' iri
					{
					match(input,32,FOLLOW_32_in_literal863); 
					pushFollow(FOLLOW_iri_in_literal865);
					iri();
					state._fsp--;

					}
					break;
				case 2 :
					// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:297:7: languageTag
					{
					pushFollow(FOLLOW_languageTag_in_literal873);
					languageTag();
					state._fsp--;

					}
					break;

			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return ;
	}
	// $ANTLR end "literal"



	// $ANTLR start "languageTag"
	// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:301:1: languageTag : LANG_TAG ;
	public final void languageTag() throws RecognitionException {
		try {
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:302:3: ( LANG_TAG )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:302:5: LANG_TAG
			{
			match(input,LANG_TAG,FOLLOW_LANG_TAG_in_languageTag893); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return ;
	}
	// $ANTLR end "languageTag"


	public static class quotedString_return extends ParserRuleReturnScope {
	};


	// $ANTLR start "quotedString"
	// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:305:1: quotedString : QUOTED_STRING ;
	public final FunctionalChangesetParser.quotedString_return quotedString() throws RecognitionException {
		FunctionalChangesetParser.quotedString_return retval = new FunctionalChangesetParser.quotedString_return();
		retval.start = input.LT(1);

		try {
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:306:3: ( QUOTED_STRING )
			// R:\\Pro\\OntoVCS\\owl2vcs\\src\\main\\java\\owl2vcs\\io\\FunctionalChangeset.g:306:5: QUOTED_STRING
			{
			match(input,QUOTED_STRING,FOLLOW_QUOTED_STRING_in_quotedString906); 
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "quotedString"

	// Delegated rules



	public static final BitSet FOLLOW_prefixes_in_changeset75 = new BitSet(new long[]{0x000000000FE40002L});
	public static final BitSet FOLLOW_setOntologyFormat_in_changeset86 = new BitSet(new long[]{0x000000000FA40002L});
	public static final BitSet FOLLOW_setOntologyId_in_changeset102 = new BitSet(new long[]{0x000000000F840002L});
	public static final BitSet FOLLOW_prefixChange_in_changeset118 = new BitSet(new long[]{0x000000000F840002L});
	public static final BitSet FOLLOW_importChange_in_changeset134 = new BitSet(new long[]{0x0000000005000002L});
	public static final BitSet FOLLOW_annotationChange_in_changeset150 = new BitSet(new long[]{0x0000000005000002L});
	public static final BitSet FOLLOW_axiomChange_in_changeset166 = new BitSet(new long[]{0x0000000005000002L});
	public static final BitSet FOLLOW_prefix_in_prefixes200 = new BitSet(new long[]{0x0000000000000402L});
	public static final BitSet FOLLOW_prefixName_in_prefix228 = new BitSet(new long[]{0x0000000010000000L});
	public static final BitSet FOLLOW_28_in_prefix230 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_fullIRI_in_prefix232 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_22_in_setOntologyFormat266 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_19_in_setOntologyFormat268 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_quotedString_in_setOntologyFormat270 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_20_in_setOntologyFormat272 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_21_in_setOntologyId296 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_ontologyIDStatement_in_setOntologyId298 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_31_in_ontologyIDStatement319 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_19_in_ontologyIDStatement321 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_oid_in_ontologyIDStatement323 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_20_in_ontologyIDStatement325 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_31_in_ontologyIDStatement333 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_19_in_ontologyIDStatement335 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_20_in_ontologyIDStatement337 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_fullIRI_in_oid358 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_fullIRI_in_oid362 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_fullIRI_in_oid374 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_25_in_prefixChange398 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_19_in_prefixChange400 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_prefix_in_prefixChange402 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_20_in_prefixChange404 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_27_in_prefixChange414 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_19_in_prefixChange416 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_prefix_in_prefixChange418 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_20_in_prefixChange420 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_23_in_prefixChange430 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_19_in_prefixChange432 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_prefix_in_prefixChange434 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_fullIRI_in_prefixChange438 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_20_in_prefixChange440 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_18_in_prefixChange450 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_19_in_prefixChange452 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_prefixName_in_prefixChange456 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_prefix_in_prefixChange458 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_20_in_prefixChange460 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_24_in_importChange484 = new BitSet(new long[]{0x0000000040000000L});
	public static final BitSet FOLLOW_importsDeclaration_in_importChange486 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_26_in_importChange496 = new BitSet(new long[]{0x0000000040000000L});
	public static final BitSet FOLLOW_importsDeclaration_in_importChange498 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_30_in_importsDeclaration519 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_19_in_importsDeclaration521 = new BitSet(new long[]{0x0000000000000210L});
	public static final BitSet FOLLOW_iri_in_importsDeclaration523 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_20_in_importsDeclaration525 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_24_in_annotationChange555 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_annotation_in_annotationChange557 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_26_in_annotationChange567 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_annotation_in_annotationChange569 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_subAnnotation_in_annotation596 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_29_in_subAnnotation613 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_19_in_subAnnotation615 = new BitSet(new long[]{0x0000000020000210L});
	public static final BitSet FOLLOW_subAnnotation_in_subAnnotation617 = new BitSet(new long[]{0x0000000020000210L});
	public static final BitSet FOLLOW_iri_in_subAnnotation620 = new BitSet(new long[]{0x0000000000010310L});
	public static final BitSet FOLLOW_annotationValue_in_subAnnotation622 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_20_in_subAnnotation624 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_iri_in_annotationValue637 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_anonymousIndividual_in_annotationValue643 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_literal_in_annotationValue649 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_24_in_axiomChange674 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_axiom_in_axiomChange676 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_26_in_axiomChange686 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_axiom_in_axiomChange688 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_subAxiom_in_axiom715 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NAME_in_subAxiom732 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_19_in_subAxiom734 = new BitSet(new long[]{0x00000000200103B0L});
	public static final BitSet FOLLOW_subAnnotation_in_subAxiom736 = new BitSet(new long[]{0x00000000200103B0L});
	public static final BitSet FOLLOW_INT_in_subAxiom739 = new BitSet(new long[]{0x0000000000010390L});
	public static final BitSet FOLLOW_argument_in_subAxiom742 = new BitSet(new long[]{0x0000000000110390L});
	public static final BitSet FOLLOW_20_in_subAxiom745 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_iri_in_argument758 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_literal_in_argument764 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_anonymousIndividual_in_argument770 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_subAxiom_in_argument776 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_fullIRI_in_iri792 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_abbreviatedIRI_in_iri798 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FULLIRI_in_fullIRI811 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PNAME_LN_in_abbreviatedIRI822 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PNAME_NS_in_prefixName833 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NODEID_in_anonymousIndividual844 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_quotedString_in_literal855 = new BitSet(new long[]{0x0000000100000042L});
	public static final BitSet FOLLOW_32_in_literal863 = new BitSet(new long[]{0x0000000000000210L});
	public static final BitSet FOLLOW_iri_in_literal865 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_languageTag_in_literal873 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LANG_TAG_in_languageTag893 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_QUOTED_STRING_in_quotedString906 = new BitSet(new long[]{0x0000000000000002L});
}
