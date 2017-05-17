# ArticleBuilderProject

Converts articles from txt file to xml.

Input text files must contain text in such order:
```
  String 1: title
  String 2: author
  String 3: hashsum of text (it may be wrong, converter will write correct hashsum into xml)
  Next strings: text
```
Output xml files have such format:
```
  <article>
  <heading>title</heading>
  <authors>author</authors>
  <hash>hashsum of text</hash>
  <text>
  ...
  </text>
  </article>
```
