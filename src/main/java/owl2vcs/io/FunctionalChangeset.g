grammar FunctionalChangeset;

options {
  language = Java;
}

@header {
package owl2vcs.io;

import java.io.IOException;
import java.io.StringReader;

import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntologyLoaderConfiguration;

import org.semanticweb.owlapi.model.OWLOntologyChange;
import org.semanticweb.owlapi.model.SetOntologyID;
import org.semanticweb.owlapi.model.OWLOntologyID;
import org.semanticweb.owlapi.model.AnnotationChange;
import org.semanticweb.owlapi.model.AddOntologyAnnotation;
import org.semanticweb.owlapi.model.RemoveOntologyAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.ImportChange;
import org.semanticweb.owlapi.model.AddImport;
import org.semanticweb.owlapi.model.RemoveImport;
import org.semanticweb.owlapi.model.OWLImportsDeclaration;
import org.semanticweb.owlapi.model.OWLAxiomChange;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.RemoveAxiom;
import org.semanticweb.owlapi.model.OWLAxiom;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import org.semanticweb.owlapi.apibinding.OWLManager;

import org.coode.owlapi.functionalparser.OWLFunctionalSyntaxParser;
import org.coode.owlapi.functionalparser.ParseException;

import org.semanticweb.owlapi.util.DefaultPrefixManager;

import owl2vcs.changeset.MutableChangeSet;
import owl2vcs.changes.OntologyChange;
import owl2vcs.changes.SetOntologyFormat;
import owl2vcs.changes.PrefixChange;
import owl2vcs.changes.AddPrefix;
import owl2vcs.changes.RemovePrefix;
import owl2vcs.changes.ModifyPrefix;
import owl2vcs.changes.RenamePrefix;
import owl2vcs.changes.UnknownOntologyFormatException;
}

@members {
private OWLOntologyManager manager;
private OWLDataFactory dataFactory;
private OWLOntology ontology;
private OWLFunctionalSyntaxParser parser;
private OWLOntology parent;

public FunctionalChangesetParser(TokenStream input, OWLOntology parent)
        throws OWLOntologyCreationException {
    this(input, new RecognizerSharedState());
    this.parent = parent;
    manager = OWLManager.createOWLOntologyManager();
    dataFactory = manager.getOWLDataFactory();
    ontology = manager.createOntology();
    parser = new OWLFunctionalSyntaxParser(new StringReader(""));
    parser.setUp(ontology, new OWLOntologyLoaderConfiguration());
}

public FunctionalChangesetParser(OWLOntology parent, String fileName)
        throws OWLOntologyCreationException, IOException {
    this(new CommonTokenStream(new FunctionalChangesetLexer(
        new ANTLRFileStream(fileName, "UTF-8"))), parent);
}

public FunctionalChangesetParser(String s)
        throws OWLOntologyCreationException, IOException {
    this(new CommonTokenStream(
        new FunctionalChangesetLexer(new ANTLRStringStream(s))),
        OWLManager.createOWLOntologyManager().createOntology());
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

}

@lexer::header {
package owl2vcs.io;
}

@lexer::members {

}

changeset returns [MutableChangeSet result]
  throws ParseException, UnknownOntologyFormatException
  :
  {
    $result = new MutableChangeSet();
  }
  prefixes
    { parser.setPrefixes($prefixes.result); }
  (setOntologyFormat
    { $result.setFormatChange($setOntologyFormat.result); }
  )?
  (setOntologyId
    { $result.setOntologyIdChange($setOntologyId.result); }
  )?
  (prefixChange
    { $result.addPrefixChange($prefixChange.result); }
  )*
  (importChange
    { $result.addImportChange($importChange.result); }
  )*
  (annotationChange
    { $result.addAnnotationChange($annotationChange.result); }
  )*
  (axiomChange
    { $result.addAxiomChange($axiomChange.result); }
  )*
  ;

prefixes returns [DefaultPrefixManager result]
  : { $result = new DefaultPrefixManager(); }
  ( prefix
    { $result.setPrefix($prefix.name, $prefix.value); }
  )*
  ;

prefix returns [String name, String value]
  : prefixName '=' fullIRI
  {
      $name = $prefixName.text;
      String s = $fullIRI.text;
      $value =  s.substring(1, s.length() - 1);
  }
  ;


// FORMAT CHANGE

setOntologyFormat
  returns [SetOntologyFormat result]
  throws UnknownOntologyFormatException
  :
  '* OntologyFormat' '(' quotedString ')'
  {
      String format = $quotedString.text.substring(1, $quotedString.text.length() - 1);
      $result = new SetOntologyFormat(parent, format);
  }
  ;


// ONTOLOGY ID CHANGE

setOntologyId returns [SetOntologyID result]
  : '+ ' ontologyIDStatement
  { $result = new SetOntologyID(parent, $ontologyIDStatement.result); }
  | '- ' ontologyIDStatement
  { $result = new SetOntologyID(parent, new OWLOntologyID()); }
  | '* ' ontologyIDStatement
  { $result = new SetOntologyID(parent, $ontologyIDStatement.result); }
  ;

ontologyIDStatement returns [OWLOntologyID result]
  : 'OntologyID' '(' oid ')' { $result = $oid.result; }
  ;

oid returns [OWLOntologyID result]
  : oiri=fullIRI viri=fullIRI
  { $result = new OWLOntologyID(parser.getIRI($oiri.text), parser.getIRI($viri.text)); }
  | oiri=fullIRI
  { $result = new OWLOntologyID(parser.getIRI($oiri.text)); }
  ;


// PREFIX CHANGES

prefixChange returns [PrefixChange result]
  : '+ Prefix' '(' prefix ')'
  { $result = new AddPrefix(parent, $prefix.name, $prefix.value); }
  | '- Prefix' '(' prefix ')'
  { $result = new RemovePrefix(parent, $prefix.name, $prefix.value); }
  | '* Prefix' '(' prefix newvalue=fullIRI ')'
  { $result = new ModifyPrefix(parent, $prefix.name, $prefix.value, $newvalue.text); }
  | '# Prefix' '(' oldname=prefixName prefix ')'
  { $result = new RenamePrefix(parent, $oldname.text, $prefix.value, $prefix.name); }
  ;


// IMPORT CHANGES

importChange returns [ImportChange result]
  : '+ ' importsDeclaration
  { $result = new AddImport(parent, $importsDeclaration.result); }
  | '- ' importsDeclaration
  { $result = new RemoveImport(parent, $importsDeclaration.result); }
  ;

importsDeclaration returns [OWLImportsDeclaration result]
  : 'Import' '(' iri ')'
  { $result = dataFactory.getOWLImportsDeclaration(parser.getIRI($iri.text)); }
  ;


// ONTOLOGY ANNOTATION CHANGES

annotationChange returns [AnnotationChange result]
  throws ParseException
  : '+ ' annotation
  { $result = new AddOntologyAnnotation(parent, $annotation.result); }
  | '- ' annotation
  { $result = new RemoveOntologyAnnotation(parent, $annotation.result); }
  ;

annotation returns [OWLAnnotation result]
  throws ParseException
  : subAnnotation
  { $result = parseAnnotation($annotation.text); }
  ;

subAnnotation
  : 'Annotation' '(' subAnnotation* iri annotationValue ')'
  ;

annotationValue
  : iri
  | anonymousIndividual
  | literal
  ;

// AXIOM CHANGES

axiomChange returns [OWLAxiomChange result]
  throws ParseException
  : '+ ' axiom
  { $result = new AddAxiom(parent, $axiom.result); }
  | '- ' axiom
  { $result = new RemoveAxiom(parent, $axiom.result); }
  ;

axiom returns [OWLAxiom result]
  throws ParseException
  : subAxiom
  { $result = parseAxiom($axiom.text); }
  ;

subAxiom
  : NAME '(' subAnnotation* INT? argument+ ')'
  ;

argument
  : iri
  | literal
  | anonymousIndividual
  | subAxiom
  ;


// COMMON DEFINITIONS

iri
  : fullIRI
  | abbreviatedIRI
  ;

fullIRI
  : FULLIRI ;

abbreviatedIRI
  : PNAME_LN ;

prefixName
  : PNAME_NS ;

anonymousIndividual
  : NODEID ;

literal
  : quotedString
    ( '^^' iri
    | languageTag
    )?
  ;

languageTag
  : LANG_TAG
  ;

quotedString
  : QUOTED_STRING
  ;

LANG_TAG
  : '@'
    ( 'A'..'Z'
    | 'a'..'z'
    | '0'..'9'
    | '-')+
  ;

INT
  : '0'..'9'+
  ;

NAME
  : 'A'..'Z'
    ( 'A'..'Z'
    | 'a'..'z'
    )+
  ;

QUOTED_STRING
  : '"'
    ( '\\' '"'
    | '\\' '\\'
    | ~('"'|'\\')
    )*
    '"'
  ;

FULLIRI
  : '<'
    (options {greedy=false;}:
      ~'>'
    )*
    '>'
  ;

PNAME_LN
  : PNAME_NS PN_LOCAL ;

NODEID
  : '_:' PN_LOCAL ;

PNAME_NS
  : PN_PREFIX? ':' ;

fragment PN_LOCAL
  : ( PN_CHARS_U | '0'..'9' ) ( ( PN_CHARS | '.' )* PN_CHARS )? ;

fragment PN_PREFIX
  : PN_CHARS_BASE ( ( PN_CHARS | '.' )* PN_CHARS )? ;

fragment PN_CHARS
  : PN_CHARS_U
  | '-'
  | '0'..'9'
  | '\u00B7'
  | '\u0300'..'\u036F'
  | '\u203F'..'\u2040'
  ;

fragment PN_CHARS_U
  : PN_CHARS_BASE
  | '_'
  ;

fragment PN_CHARS_BASE
  : 'A'..'Z'
  | 'a'..'z'
  | '\u00C0'..'\u00D6'
  | '\u00D8'..'\u00F6'
  | '\u00F8'..'\u02FF'
  | '\u0370'..'\u037D'
  | '\u037F'..'\u1FFF'
  | '\u200C'..'\u200D'
  | '\u2070'..'\u218F'
  | '\u2C00'..'\u2FEF'
  | '\u3001'..'\uD7FF'
  | '\uF900'..'\uFDCF'
  | '\uFDF0'..'\uFFFD'
  ;

WS : (' ' | '\t' | '\n' | '\r' | '\f')+ {$channel = HIDDEN;} ;
