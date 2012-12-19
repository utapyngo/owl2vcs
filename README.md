owl2vcs is a set of tools designed to facilitate version control of [OWL 2
ontologies][1] using version control systems.

## Contents

-   owl2diff: a command line tool for comparing OWL 2 ontologies;

-   owl2merge: a three-way merge tool for OWL 2 ontologies;

-   a set of scripts to integrate the afore-mentioned tools with [Git][2],
    [Mercurial][3] and [Subversion][4].

## Requirements

-   Java 1.6 or higher and `java` in `PATH`;

-   For [Git][2]: `git` in `PATH;`

-   For [Mercurial][3]: `hg` in `PATH`;

## Installation instructions

 1. Download;
 2. Unpack;
 3. Add to `PATH`.

## Standalone usage

After adding the directory to `PATH` you can use the `owl2diff` command to compare two versions of an ontology. See `owl2diff --help` for more information.

## Usage with Git/Mercurial

 1. Open command shell and `cd` into your repository;
 2. Type `owl2enable`;
 3. Now you can view informative diffs for *.owl, *.rdf, and *.ttl files with either `hg owl2diff` or `git difftool`.

[1]: http://www.w3.org/TR/owl2-overview/

[2]: http://git-scm.com/

[3]: http://mercurial.selenic.com/

[4]: http://subversion.apache.org/
