# API Docs - v1.0.2

!!! Info "Tested Siddhi Core version: *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/">5.1.21</a>*"
    It could also support other Siddhi Core minor versions.

## List

### collect *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#aggregate-function">(Aggregate Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">Collects multiple values to construct a list.</p>
<p></p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<OBJECT> list:collect(<OBJECT|INT|LONG|FLOAT|DOUBLE|BOOL|STRING> value)
<OBJECT> list:collect(<OBJECT|INT|LONG|FLOAT|DOUBLE|BOOL|STRING> value, <BOOL> is.distinct)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">value</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">Value of the list element</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">OBJECT<br>INT<br>LONG<br>FLOAT<br>DOUBLE<br>BOOL<br>STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">is.distinct</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">If <code>true</code> only distinct elements are collected</p></td>
        <td style="vertical-align: top">false</td>
        <td style="vertical-align: top">BOOL</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
from StockStream#window.lengthBatch(10)
select list:collect(symbol) as stockSymbols
insert into OutputStream;
```
<p></p>
<p style="word-wrap: break-word;margin: 0;">For the window expiry of 10 events, the collect() function will collect attributes of <code>symbol</code> to a single list and return as stockSymbols.</p>
<p></p>
### merge *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#aggregate-function">(Aggregate Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">Collects multiple lists to merge as a single list.</p>
<p></p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<OBJECT> list:merge(<OBJECT> list)
<OBJECT> list:merge(<OBJECT> list, <BOOL> is.distinct)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">list</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">List to be merged</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">is.distinct</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">Whether to return list with distinct values</p></td>
        <td style="vertical-align: top">false</td>
        <td style="vertical-align: top">BOOL</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
from StockStream#window.lengthBatch(2)
select list:merge(list) as stockSymbols
insert into OutputStream;
```
<p></p>
<p style="word-wrap: break-word;margin: 0;">For the window expiry of 2 events, the merge() function will collect attributes of <code>list</code> and merge them to a single list, returned as stockSymbols.</p>
<p></p>
### add *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">Function returns the updated list after adding the given value.</p>
<p></p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<OBJECT> list:add(<OBJECT> list, <OBJECT|INT|LONG|FLOAT|DOUBLE|BOOL|STRING> value)
<OBJECT> list:add(<OBJECT> list, <OBJECT|INT|LONG|FLOAT|DOUBLE|BOOL|STRING> value, <INT> index)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">list</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The list to which the value should be added.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">value</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The value to be added.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">OBJECT<br>INT<br>LONG<br>FLOAT<br>DOUBLE<br>BOOL<br>STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">index</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The index in which the value should to be added.</p></td>
        <td style="vertical-align: top">last</td>
        <td style="vertical-align: top">INT</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
list:add(stockSymbols, 'IBM')
```
<p></p>
<p style="word-wrap: break-word;margin: 0;">Function returns the updated list after adding the value <code>IBM</code> in the last index.</p>
<p></p>
<span id="example-2" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 2</span>
```
list:add(stockSymbols, 'IBM', 0)
```
<p></p>
<p style="word-wrap: break-word;margin: 0;">Function returns the updated list after adding the value <code>IBM</code> in the 0th index`.</p>
<p></p>
### addAll *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">Function returns the updated list after adding all the values from the given list.</p>
<p></p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<OBJECT> list:addAll(<OBJECT> to.list, <OBJECT> from.list)
<OBJECT> list:addAll(<OBJECT> to.list, <OBJECT> from.list, <BOOL> is.distinct)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">to.list</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The list into which the values need to copied.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">from.list</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The list from which the values are copied.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">is.distinct</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">If <code>true</code> returns list with distinct values</p></td>
        <td style="vertical-align: top">false</td>
        <td style="vertical-align: top">BOOL</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
list:putAll(toList, fromList)
```
<p></p>
<p style="word-wrap: break-word;margin: 0;">If <code>toList</code> contains values ('IBM', 'WSO2), and if <code>fromList</code> contains values ('IBM', 'XYZ') then the function returns updated <code>toList</code> with values ('IBM', 'WSO2', 'IBM', 'XYZ').</p>
<p></p>
<span id="example-2" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 2</span>
```
list:putAll(toList, fromList, true)
```
<p></p>
<p style="word-wrap: break-word;margin: 0;">If <code>toList</code> contains values ('IBM', 'WSO2), and if <code>fromList</code> contains values ('IBM', 'XYZ') then the function returns updated <code>toList</code> with values ('IBM', 'WSO2', 'XYZ').</p>
<p></p>
### clear *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">Function returns the cleared list. </p>
<p></p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<OBJECT> list:clear(<OBJECT> list)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">list</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The list which needs to be cleared</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
list:clear(stockDetails)
```
<p></p>
<p style="word-wrap: break-word;margin: 0;">Returns an empty list.</p>
<p></p>
### clone *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">Function returns the cloned list.</p>
<p></p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<OBJECT> list:clone(<OBJECT> list)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">list</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The list to which needs to be cloned.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
list:clone(stockSymbols)
```
<p></p>
<p style="word-wrap: break-word;margin: 0;">Function returns cloned list of stockSymbols.</p>
<p></p>
### contains *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">Function checks whether the list contains the specific value.</p>
<p></p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<BOOL> list:contains(<OBJECT> list, <OBJECT|INT|LONG|FLOAT|DOUBLE|BOOL|STRING> value)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">list</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The list that needs to be checked on whether it contains the value or not.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">value</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The value that needs to be checked.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">OBJECT<br>INT<br>LONG<br>FLOAT<br>DOUBLE<br>BOOL<br>STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
list:contains(stockSymbols, 'IBM')
```
<p></p>
<p style="word-wrap: break-word;margin: 0;">Returns 'true' if the stockSymbols list contains value <code>IBM</code> else it returns <code>false</code>.</p>
<p></p>
### containsAll *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">Function checks whether the list contains all the values in the given list.</p>
<p></p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<BOOL> list:containsAll(<OBJECT> list, <OBJECT> given.list)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">list</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The list that needs to be checked on whether it contains all the values or not.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">given.list</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The list which contains all the values to be checked.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
list:containsAll(stockSymbols, latestStockSymbols)
```
<p></p>
<p style="word-wrap: break-word;margin: 0;">Returns 'true' if the stockSymbols list contains values in latestStockSymbols else it returns <code>false</code>.</p>
<p></p>
### containsAny *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">Function checks whether the two specified lists have any common elements.</p>
<p></p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<BOOL> list:containsAny(<OBJECT> list, <OBJECT> given.list)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">list</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The list that needs to be checked on whether it contains any of the values or not.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">given.list</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The list which contains any of the values to be checked.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
list:containsAny(stockSymbols, latestStockSymbols)
```
<p></p>
<p style="word-wrap: break-word;margin: 0;">Returns 'true' if the stockSymbols list contains any value in latestStockSymbols else it returns <code>false</code>.</p>
<p></p>
### create *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">Function creates a list containing all values provided.</p>
<p></p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<OBJECT> list:create()
<OBJECT> list:create(<OBJECT|INT|LONG|FLOAT|DOUBLE|BOOL|STRING> value1)
<OBJECT> list:create(<OBJECT|INT|LONG|FLOAT|DOUBLE|BOOL|STRING> value1, <OBJECT|INT|LONG|FLOAT|DOUBLE|BOOL|STRING> ...)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">value1</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">Value 1</p></td>
        <td style="vertical-align: top"><EMPTY_STRING></td>
        <td style="vertical-align: top">OBJECT<br>INT<br>LONG<br>FLOAT<br>DOUBLE<br>BOOL<br>STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
list:create(1, 2, 3, 4, 5, 6)
```
<p></p>
<p style="word-wrap: break-word;margin: 0;">This returns a list with values <code>1</code>, <code>2</code>, <code>3</code>, <code>4</code>, <code>5</code> and <code>6</code>.</p>
<p></p>
<span id="example-2" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 2</span>
```
list:create()
```
<p></p>
<p style="word-wrap: break-word;margin: 0;">This returns an empty list.</p>
<p></p>
### get *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">Function returns the value at the specific index, null if index is out of range.</p>
<p></p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<OBJECT|INT|LONG|FLOAT|DOUBLE|BOOL|STRING> list:get(<OBJECT> list, <INT> index)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">list</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">Attribute containing the list</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">index</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">Index of the element</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
list:get(stockSymbols, 1)
```
<p></p>
<p style="word-wrap: break-word;margin: 0;">This returns the element in the 1st index in the stockSymbols list.</p>
<p></p>
### indexOf *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">Function returns the last index of the given element.</p>
<p></p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<INT> list:indexOf(<OBJECT> list, <OBJECT|INT|LONG|FLOAT|DOUBLE|BOOL|STRING> value)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">list</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The list to be checked to get index of an element.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">value</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">Value for which last index needs to be identified.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">OBJECT<br>INT<br>LONG<br>FLOAT<br>DOUBLE<br>BOOL<br>STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
list:indexOf(stockSymbols. `IBM`)
```
<p></p>
<p style="word-wrap: break-word;margin: 0;">Returns the last index of the element <code>IBM</code> if present else it returns -1.</p>
<p></p>
### isEmpty *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">Function checks if the list is empty.</p>
<p></p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<BOOL> list:isEmpty(<OBJECT> list)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">list</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The list that needs to be checked whether it's empty or not.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
list:isEmpty(stockSymbols)
```
<p></p>
<p style="word-wrap: break-word;margin: 0;">Returns 'true' if the stockSymbols list is empty else it returns <code>false</code>.</p>
<p></p>
### isList *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">Function checks if the object is type of a list.</p>
<p></p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<BOOL> list:isList(<OBJECT|INT|LONG|FLOAT|DOUBLE|BOOL|STRING> arg)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">arg</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The argument the need to be determined whether it's a list or not.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">OBJECT<br>INT<br>LONG<br>FLOAT<br>DOUBLE<br>BOOL<br>STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
list:isList(stockSymbols)
```
<p></p>
<p style="word-wrap: break-word;margin: 0;">Returns 'true' if the stockSymbols is and an instance of <code>java.util.List</code> else it returns <code>false</code>.</p>
<p></p>
### lastIndexOf *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">Function returns the index of the given value.</p>
<p></p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<INT> list:lastIndexOf(<OBJECT> list, <OBJECT|INT|LONG|FLOAT|DOUBLE|BOOL|STRING> value)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">list</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The list to be checked to get index of an element.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">value</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">Value for which last index needs to be identified.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">OBJECT<br>INT<br>LONG<br>FLOAT<br>DOUBLE<br>BOOL<br>STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
list:lastIndexOf(stockSymbols. `IBM`)
```
<p></p>
<p style="word-wrap: break-word;margin: 0;">Returns the last index of the element <code>IBM</code> if present else it returns -1.</p>
<p></p>
### remove *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">Function returns the updated list after removing the element with the specified value.</p>
<p></p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<OBJECT> list:remove(<OBJECT> list, <OBJECT|INT|LONG|FLOAT|DOUBLE|BOOL|STRING> value)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">list</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The list that needs to be updated.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">value</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The value of the element that needs to removed.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">OBJECT<br>INT<br>LONG<br>FLOAT<br>DOUBLE<br>BOOL<br>STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
list:remove(stockSymbols, 'IBM')
```
<p></p>
<p style="word-wrap: break-word;margin: 0;">This returns the updated list, stockSymbols after stockSymbols the value <code>IBM</code>.</p>
<p></p>
### removeAll *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">Function returns the updated list after removing all the element with the specified list.</p>
<p></p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<OBJECT> list:removeAll(<OBJECT> list, <OBJECT> given.list)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">list</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The list that needs to be updated.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">given.list</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The list with all the elements that needs to removed.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
list:removeAll(stockSymbols, latestStockSymbols)
```
<p></p>
<p style="word-wrap: break-word;margin: 0;">This returns the updated list, stockSymbols after removing all the values in latestStockSymbols.</p>
<p></p>
### removeByIndex *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">Function returns the updated list after removing the element with the specified index.</p>
<p></p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<OBJECT> list:removeByIndex(<OBJECT> list, <INT> index)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">list</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The list that needs to be updated.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">index</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The index of the element that needs to removed.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
list:removeByIndex(stockSymbols, 0)
```
<p></p>
<p style="word-wrap: break-word;margin: 0;">This returns the updated list, stockSymbols after removing value at 0 th index.</p>
<p></p>
### retainAll *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">Function returns the updated list after retaining all the elements in the specified list.</p>
<p></p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<OBJECT> list:retainAll(<OBJECT> list, <OBJECT> given.list)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">list</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The list that needs to be updated.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">given.list</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The list with all the elements that needs to reatined.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
list:retainAll(stockSymbols, latestStockSymbols)
```
<p></p>
<p style="word-wrap: break-word;margin: 0;">This returns the updated list, stockSymbols after retaining all the values in latestStockSymbols.</p>
<p></p>
### setValue *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">Function returns the updated list after replacing the element in the given index by the given value.</p>
<p></p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<OBJECT> list:setValue(<OBJECT> list, <INT> index, <OBJECT|INT|LONG|FLOAT|DOUBLE|BOOL|STRING> value)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">list</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The list to which the value should be updated.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">index</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The index in which the value should to be updated.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">INT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">value</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The value to be updated with.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">OBJECT<br>INT<br>LONG<br>FLOAT<br>DOUBLE<br>BOOL<br>STRING</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
list:set(stockSymbols, 0, 'IBM')
```
<p></p>
<p style="word-wrap: break-word;margin: 0;">Function returns the updated list after replacing the value at 0th index with the value <code>IBM</code></p>
<p></p>
### size *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">Function to return the size of the list.</p>
<p></p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<INT> list:size(<OBJECT> list)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">list</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The list for which size should be returned.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
list:size(stockSymbols)
```
<p></p>
<p style="word-wrap: break-word;margin: 0;">Returns size of the <code>stockSymbols</code> list.</p>
<p></p>
### sort *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#function">(Function)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">Function returns lists sorted in ascending or descending order.</p>
<p></p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
<OBJECT> list:sort(<OBJECT> list)
<OBJECT> list:sort(<OBJECT> list, <STRING> order)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">list</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">The list which should be sorted.</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
    <tr>
        <td style="vertical-align: top">order</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">Order in which the list needs to be sorted (ASC/DESC/REV).</p></td>
        <td style="vertical-align: top">REV</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
list:sort(stockSymbols)
```
<p></p>
<p style="word-wrap: break-word;margin: 0;">Function returns the sorted list in ascending order.</p>
<p></p>
<span id="example-2" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 2</span>
```
list:sort(stockSymbols, 'DESC')
```
<p></p>
<p style="word-wrap: break-word;margin: 0;">Function returns the sorted list in descending  order.</p>
<p></p>
### tokenize *<a target="_blank" href="http://siddhi.io/en/v5.1/docs/query-guide/#stream-processor">(Stream Processor)</a>*
<p></p>
<p style="word-wrap: break-word;margin: 0;">Tokenize the list and return each key, value as new attributes in events</p>
<p></p>
<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>

```
list:tokenize(<OBJECT> list)
list:tokenize(<OBJECT> list, <OBJECT> ...)
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">list</td>
        <td style="vertical-align: top; word-wrap: break-word"><p style="word-wrap: break-word;margin: 0;">Array list which needs to be tokenized</p></td>
        <td style="vertical-align: top"></td>
        <td style="vertical-align: top">OBJECT</td>
        <td style="vertical-align: top">No</td>
        <td style="vertical-align: top">Yes</td>
    </tr>
</table>
<span id="extra-return-attributes" class="md-typeset" style="display: block; font-weight: bold;">Extra Return Attributes</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Possible Types</th>
    </tr>
    <tr>
        <td style="vertical-align: top">index</td>
        <td style="vertical-align: top;"><p style="word-wrap: break-word;margin: 0;">Index of an entry consisted in the list</p></td>
        <td style="vertical-align: top">INT</td>
    </tr>
    <tr>
        <td style="vertical-align: top">value</td>
        <td style="vertical-align: top;"><p style="word-wrap: break-word;margin: 0;">Value of an entry consisted in the list</p></td>
        <td style="vertical-align: top">OBJECT</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
list:tokenize(customList)
```
<p></p>
<p style="word-wrap: break-word;margin: 0;">If custom list contains ('WSO2', 'IBM', 'XYZ') elements, then tokenize function will return 3 events with value attributes WSO2, IBM and XYZ respectively.</p>
<p></p>
