[![Build Status](https://github.com/mP1/walkingkooka-tree-expression-function-string/actions/workflows/build.yaml/badge.svg)](https://github.com/mP1/walkingkooka-tree-expression-function-string/actions/workflows/build.yaml/badge.svg)
[![Coverage Status](https://coveralls.io/repos/github/mP1/walkingkooka-tree-expression-function-string/badge.svg?branch=master)](https://coveralls.io/github/mP1/walkingkooka-tree-expression-function-string?branch=master)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/mP1/walkingkooka-tree-expression-function-string.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/mP1/walkingkooka-tree-expression-function-string/context:java)
[![Total alerts](https://img.shields.io/lgtm/alerts/g/mP1/walkingkooka-tree-expression-function-string.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/mP1/walkingkooka-tree-expression-function-string/alerts/)
[![J2CL compatible](https://img.shields.io/badge/J2CL-compatible-brightgreen.svg)](https://github.com/mP1/j2cl-central)


# walkingkooka-tree-expression-function-string
A collection of ExpressionFunction(s) that operate and return string(text) results. Many of the functions follow Excel
semantics.



## Available functions

- ascii(number)
- char(number)
- clean(string)
- concat(string...)
- contains-case-insensitive(string, string)
- contains-case-sensitive(string, string)
- ends-with-case-insensitive(string, string)
- ends-with-case-sensitive(string, string)
- find-case-insensitive(string, string, number*)
- find-case-sensitive(string, string, number*)
- isNonText(value)
- isText(value)
- left(string, number)
- lower-case(string)
- mid(string, number, number*)
- normalize-space(string)
- proper(string)
- repeat(string, number)
- replace(String, number, number, String)
- right(string, number)
- search-case-insensitive(string, string, number*)
- search-case-sensitive(string, string, number*)
- space-trim(string)
- string-equals-case-insensitive(string, string)
- string-equals-case-sensitive(string, string)
- starts-with-case-insensitive(string, string)
- starts-with-case-sensitive(string, string)
- string-length(string)
- substitute(string, string, string, number?)
- substring-after(string, string, number*)
- substring-before(string, string, number*)
- t(object)
- text(object)
- text-join(string delimiter, boolean ignoreEmpty, string text, string more-text)
- trim(string)
- trim-left(string)
- trim-right(string)
- unicode(string)
- upper-case(string)

Many more functions are outstanding and remain TODO.

