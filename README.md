# FiniteAutomata Converter (NFA to DFA)
This is a programming assignment for Compiler Design course.

The program is required to convert a NFA to DFA using the following algorithm:
```
put  e-closure({s0}) as an unmarked  state into the set of DFA (DS)
while (there is one unmarked S1 in DS) do 
	begin
		  mark S1
		  for each input symbol a do 
		      begin
		          S2 <- e-closure(move(S1,a))
		          if (S2 is not in DS) then
			          add S2 into DS as an unmarked state
		          transfunc[S1,a] <- S2
		      end
	end
```

## Screenshots:
#### Input
![alt text](https://github.com/mAlaliSy/FiniteAutomata/raw/master/screenshots/NFA(Input).png "Input")
#### Output:
![alt text](https://github.com/mAlaliSy/FiniteAutomata/blob/master/screenshots/DFA(Output).png "Output")

### A demonstration video:
<a href="http://www.youtube.com/watch?feature=player_embedded&v=MVones8LFrk
" target="_blank"><img src="http://img.youtube.com/vi/MVones8LFrk/0.jpg" 
alt="Demonstration Video" width="240" height="180" border="10" /></a>
