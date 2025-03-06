Siddhi Execution List
======================================

  [![Jenkins Build Status](https://wso2.org/jenkins/job/siddhi/job/siddhi-execution-list/badge/icon)](https://wso2.org/jenkins/job/siddhi/job/siddhi-execution-list/)
  [![GitHub Release](https://img.shields.io/github/release/siddhi-io/siddhi-execution-list.svg)](https://github.com/siddhi-io/siddhi-execution-list/releases)
  [![GitHub Release Date](https://img.shields.io/github/release-date/siddhi-io/siddhi-execution-list.svg)](https://github.com/siddhi-io/siddhi-execution-list/releases)
  [![GitHub Open Issues](https://img.shields.io/github/issues-raw/siddhi-io/siddhi-execution-list.svg)](https://github.com/siddhi-io/siddhi-execution-list/issues)
  [![GitHub Last Commit](https://img.shields.io/github/last-commit/siddhi-io/siddhi-execution-list.svg)](https://github.com/siddhi-io/siddhi-execution-list/commits/master)
  [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

The **siddhi-execution-list extension** is a <a target="_blank" href="https://siddhi.io/">Siddhi</a> extension that provides capability to generate and manipulate list data objects.

For information on <a target="_blank" href="https://siddhi.io/">Siddhi</a> and it's features refer <a target="_blank" href="https://siddhi.io/redirect/docs.html">Siddhi Documentation</a>. 

## Download

* Versions with group id `io.siddhi.extension.*` from <a target="_blank" href="https://mvnrepository.com/artifact/io.siddhi.extension.execution.list/siddhi-execution-list/">here</a>.

## Latest API Docs 

Latest API Docs is <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api/1.0.2">1.0.2</a>.

## Features

* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api/1.0.2/#collect-aggregate-function">collect</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#aggregate-function">Aggregate Function</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Collects multiple values to construct a list.</p></p></div>
* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api/1.0.2/#merge-aggregate-function">merge</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#aggregate-function">Aggregate Function</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Collects multiple lists to merge as a single list.</p></p></div>
* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api/1.0.2/#add-function">add</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">Function</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Function returns the updated list after adding the given value.</p></p></div>
* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api/1.0.2/#addall-function">addAll</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">Function</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Function returns the updated list after adding all the values from the given list.</p></p></div>
* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api/1.0.2/#clear-function">clear</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">Function</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Function returns the cleared list. </p></p></div>
* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api/1.0.2/#clone-function">clone</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">Function</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Function returns the cloned list.</p></p></div>
* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api/1.0.2/#contains-function">contains</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">Function</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Function checks whether the list contains the specific value.</p></p></div>
* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api/1.0.2/#containsall-function">containsAll</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">Function</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Function checks whether the list contains all the values in the given list.</p></p></div>
* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api/1.0.2/#containsany-function">containsAny</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">Function</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Function checks whether the two specified lists have any common elements.</p></p></div>
* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api/1.0.2/#create-function">create</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">Function</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Function creates a list containing all values provided.</p></p></div>
* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api/1.0.2/#get-function">get</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">Function</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Function returns the value at the specific index, null if index is out of range.</p></p></div>
* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api/1.0.2/#indexof-function">indexOf</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">Function</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Function returns the last index of the given element.</p></p></div>
* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api/1.0.2/#isempty-function">isEmpty</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">Function</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Function checks if the list is empty.</p></p></div>
* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api/1.0.2/#islist-function">isList</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">Function</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Function checks if the object is type of a list.</p></p></div>
* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api/1.0.2/#lastindexof-function">lastIndexOf</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">Function</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Function returns the index of the given value.</p></p></div>
* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api/1.0.2/#remove-function">remove</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">Function</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Function returns the updated list after removing the element with the specified value.</p></p></div>
* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api/1.0.2/#removeall-function">removeAll</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">Function</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Function returns the updated list after removing all the element with the specified list.</p></p></div>
* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api/1.0.2/#removebyindex-function">removeByIndex</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">Function</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Function returns the updated list after removing the element with the specified index.</p></p></div>
* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api/1.0.2/#retainall-function">retainAll</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">Function</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Function returns the updated list after retaining all the elements in the specified list.</p></p></div>
* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api/1.0.2/#setvalue-function">setValue</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">Function</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Function returns the updated list after replacing the element in the given index by the given value.</p></p></div>
* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api/1.0.2/#size-function">size</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">Function</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Function to return the size of the list.</p></p></div>
* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api/1.0.2/#sort-function">sort</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">Function</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Function returns lists sorted in ascending or descending order.</p></p></div>
* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api/1.0.2/#tokenize-stream-processor">tokenize</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#stream-processor">Stream Processor</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Tokenize the list and return each key, value as new attributes in events</p></p></div>

## Dependencies 

There are no other dependencies needed for this extension. 

## Installation

For installing this extension on various Siddhi execution environments refer Siddhi documentation section on <a target="_blank" href="https://siddhi.io/redirect/add-extensions.html">adding extensions</a>.

## Support and Contribution

* We encourage users to ask questions and get support via <a target="_blank" href="https://stackoverflow.com/questions/tagged/siddhi">StackOverflow</a>, make sure to add the `siddhi` tag to the issue for better response.

* If you find any issues related to the extension please report them on <a target="_blank" href="https://github.com/siddhi-io/siddhi-execution-list/issues">the issue tracker</a>.

* For production support and other contribution related information refer <a target="_blank" href="https://siddhi.io/community/">Siddhi Community</a> documentation.
