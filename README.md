owl2vcs is a set of tools designed to facilitate version control of  [OWL 2
ontologies][1] using version control systems.



Contents
--------

-   owl2diff - a command line diff tool for OWL 2 ontologies;

-   a set of scripts to integrate the tools with [Git][2], [Mercurial][3] and
    [Subversion][4].



Features
--------

-   Detects axioms additions and removals;

-   Detects imports additions and removals;

-   Detects ontology annotations additions and removals;

-   Detects prefix additions, removals, modifications and renames;

-   Detects ontology IRI and version IRI changes;

-   Detects ontology format changes;

-   Supports RDF/XML, OWL/XML, OWL Functional Syntax, Manchester OWL Syntax,
    Turtle;

-   Changeset serializer and parser;

-   Two formats of changes: compact (like OWL Functional Syntax) and indented
    (same but uses indents instead of parentheses, more readable);

-   Four formats of IRIs: Simple, QName, Full, Label.



Requirements
------------

-   Java 1.6 or higher and `java` in `PATH`;

-   For [Git][2]: `git` in `PATH;`

-   For [Mercurial][3]: `hg` in `PATH`;



Installation instructions
-------------------------

1.  Download;

2.  Unpack;

3.  Add to `PATH`.



Standalone usage
----------------

After adding the directory to `PATH` you can use the `owl2diff` command to
compare two versions of an ontology. See `owl2diff --help` for more information.



Usage with Git/Mercurial
------------------------

1.  Open command shell and `cd` into your repository;

2.  Type `owl2enable`;

3.  Now you can view informative diffs for *.owl, *.rdf, and \*.ttl files with
    either `hg owl2diff` or `git difftool`.

[1]: http://www.w3.org/TR/owl2-overview/

[2]: http://git-scm.com/

[3]: http://mercurial.selenic.com/

[4]: http://subversion.apache.org/
