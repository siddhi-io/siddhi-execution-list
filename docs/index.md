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

Latest API Docs is <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api/"></a>.

## Features

* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api//#collect-aggregate-function">collect</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#aggregate-function">Aggregate Function</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Collect multiple values to construct a list.</p></p></div>
* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api//#merge-aggregate-function">merge</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#aggregate-function">Aggregate Function</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Collect multiple list to merge as a single list.</p></p></div>
* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api//#add-function">add</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">Function</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Function returns the updated list after adding the given value.</p></p></div>
* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api//#addall-function">addAll</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">Function</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Function returns the updated list after adding all the values from another list.</p></p></div>
* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api//#contains-function">contains</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">Function</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Function checks if the list contains the value.</p></p></div>
* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api//#containsall-function">containsAll</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">Function</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Function checks if the list contains all the values.</p></p></div>
* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api//#create-function">create</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">Function</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Function creates a list of all values.</p></p></div>
* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api//#get-function">get</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">Function</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Function returns the value at the specific index, null if index is out of range.</p></p></div>
* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api//#indexof-function">indexOf</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">Function</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Function returns the last index of the given element.</p></p></div>
* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api//#isempty-function">isEmpty</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">Function</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Function checks if the list is empty.</p></p></div>
* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api//#islist-function">isList</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">Function</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Function checks if the object is type of a list.</p></p></div>
* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api//#lastindexof-function">lastIndexOf</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">Function</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Function returns the index of the given value.</p></p></div>
* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api//#remove-function">remove</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">Function</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Function returns the updated list after removing the element with the specified value.</p></p></div>
* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api//#removeall-function">removeAll</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">Function</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Function returns the updated list after removing all the element with the specified list.</p></p></div>
* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api//#size-function">size</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">Function</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Function to return the size of the list.</p></p></div>
* <a target="_blank" href="https://siddhi-io.github.io/siddhi-execution-list/api//#tokenize-stream-processor">tokenize</a> *(<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#stream-processor">Stream Processor</a>)*<br> <div style="padding-left: 1em;"><p><p style="word-wrap: break-word;margin: 0;">Tokenize the list and return each key, value as new attributes in events</p></p></div>

## Dependencies 

There are no other dependencies needed for this extension. 

## Installation

For installing this extension on various Siddhi execution environments refer Siddhi documentation section on <a target="_blank" href="https://siddhi.io/redirect/add-extensions.html">adding extensions</a>.

## Support and Contribution

* We encourage users to ask questions and get support via <a target="_blank" href="https://stackoverflow.com/questions/tagged/siddhi">StackOverflow</a>, make sure to add the `siddhi` tag to the issue for better response.

* If you find any issues related to the extension please report them on <a target="_blank" href="https://github.com/siddhi-io/siddhi-execution-list/issues">the issue tracker</a>.

* For production support and other contribution related information refer <a target="_blank" href="https://siddhi.io/community/">Siddhi Community</a> documentation.
