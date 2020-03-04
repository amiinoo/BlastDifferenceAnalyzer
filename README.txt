Take the BLAST analysis and paste the text into the file blast.txt, then run the program. 
The program output will be written into output.txt.

The necessary files to edit are within the /src folder, but symbolic links are present in the overall project folder.

Please format the input BLAST file as such (or use the current blast.txt as an example), otherwise the program WON'T work: 

(LINE 1)>Gordonia phage Feastonyeet complete sequence, 47166 bp including
             13-base 3' overhang (CGGCGGTAGGCTT), Cluster CT
          Length = 47166

 Score = 9.348e+04 bits (47154), Expect = 0.0
 Identities = 47163/47166 (99%)
 Strand = Plus / Plus

                                                                         
(LINE 10) Query: 1     tgtcgctgacgctgtttacagatgccaacaacttttctatgaaaggagtggcgcgatgcc 60
             ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
(LINE 12) Sbjct: 1     tgtcgctgacgctgtttacagatgccaacaacttttctatgaaaggagtggcgcgatgcc 60

                                                                         
(LINE 15) Query: 61    acgcagaggaggggccgcccccaagagcccagaccagatgacaaattccacacgggcgaa 120
             ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
(LINE 17) Sbjct: 61    acgcagaggaggggccgcccccaagagcccagaccagatgacaaattccacacgggcgaa 120

and so on and so forth.
                                                                         