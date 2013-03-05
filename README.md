owl2vcs is a set of tools designed to facilitate version control of  [OWL 2 ontologies][owl2] using version control systems.



Contents
--------

-   owl2diff - a command line diff tool for OWL 2 ontologies;

-   owl2merge - a three-way merge tool for OWL 2 ontologies;

-   a set of scripts to integrate the tools with [Git][git], [Mercurial][hg] and [Subversion][svn].



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

-   For [Git][git]: `git` in `PATH;`

-   For [Mercurial][hg]: `hg` in `PATH`;



Installation instructions
-------------------------

1.  [Download][owl2vcs-latest];

2.  Unzip;

3.  [Add][path] to `PATH`.



Standalone usage
----------------

After adding the directory to `PATH` you can use the `owl2diff` command to compare two versions of an ontology. See `owl2diff --help` for more information.



Usage with Git/Mercurial
------------------------

1.  Open command shell and `cd` into your repository;

2.  Type `owl2enable`;

3.  Now you can view informative diffs for \*.owl, \*.rdf, and \*.ttl files with either `hg owl2diff` or `git diff`.
  * If `git diff` hangs on Windows, use `sh -c "git diff"` or `git difftool`.

4.  If you want owl2vcs to compare files with other extensions, edit your `.hg/hgrc` or `.git/info/attributes`.



Please help out
---------------

This project is still under development. Feedback and suggestions are very welcome and I encourage you to use the [Issues list][issues] on Github to provide that feedback.

Feel free to [fork][fork] this repo and to commit your additions.

Contributing
------------

1.  [Fork it][fork].

2.  Clone the **develop** branch to your machine: `git clone -b develop git@github.com:utapyngo/owl2vcs.git`.

3.  Create your feature branch: `git checkout -b my-new-feature`.

4.  Commit your changes: `git commit -am 'Added some feature'`.

5.  Push to the branch `git push origin my-new-feature`.

6.  Create new Pull Request.

[owl2]:   http://www.w3.org/TR/owl2-overview/

[git]:    http://git-scm.com/

[hg]:     http://mercurial.selenic.com/

[svn]:    http://subversion.apache.org/

[owl2vcs-latest]: http://j.mp/owl2vcs-latest

[path]:   https://github.com/utapyngo/owl2vcs/wiki/How-to-add-owl2vcs-to-PATH

[issues]: http://github.com/utapyngo/owl2vcs/issues

[fork]:   https://github.com/utapyngo/owl2vcs/fork_select