# N-gram_Frequency_Builder
 N-gram Frequency Builder project for Object-Oriented Programming module

Original ReadMe:
Program intakes user specified folder of text files, breaks folder
of files down progressively from lines to words and finally to 
n-grams with a user-defined size for n. 
A table is created with a column of unique n-grams and a second 
column with a count of occurrences of each n-gram across all 
parsed text files. 
Non-null table entries are counted and a new table is created to 
hold only the populated rows, then the final table is saved to a 
.csv file named by the user, found in the source folder.
When compiling, main method is in Runner class. 