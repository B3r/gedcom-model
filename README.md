Gedcom Model:
-
This is my own try to transfer the gedcom 5.5.1 specifications into a java model  
The source of the specification is
[~~this Page~~](http://homepages.rootsweb.com/~pmcbride/gedcom/55gcch2.htm)  
As a test I used the example at the end of that same page, however I modified it slightly, please compare the original file with my own updates

v0.2: Updated the codebase according to specification pdf (see /specification/gedcom-5.5.1.pdf)  
As a test I used the example in the pdf, however I modified it slightly.
Modifications of example file:  
- changed order
- changed some inconsistencies regarding capitalization
- changed some number formatting
- changed 'Microfilm' to 'film'


Main Class is `GedcomModel`  
#### Warning: This model is not a valid gedcom model yet!  
Use on your own responsibility  
Following elements are still in work (no guarantee on completeness of list): 

- Dates reduced to Gregorian Dates  
 If other dates needed as of now, implement `IDate`  
- `MultimediaRecord` not yet implemented

- File Types **_not_** restricted to  
[ bmp | gif | jpeg | ole | pcx | tiff | wav ]
