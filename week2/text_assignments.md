## 6.1
### a
if the graph is a path, with three nodes [a,b,c] with edges [[a,b][b,c]]
and having the weights [4,5,4], then the first step would remove the "b" node, and wouldn't get the maximum independent set.

### b
If the graph is a path, with four nodes [a,b,c,d] with edges [[a,b][b,c],[c,d]]
and having the weights [5,1,1,4], then S<sub>1</sub> and S<sub>2</sub> would be [a,c] and [b,d] respectively, and the algorithm would return [a,c] as the maximum independent set. When the max set is [a,d].

### c
Pseudocode:
```
max_independent_set(G):
    #base case
    if G is empty:
        return 0
    #recursive case
    #remove the first node and all its neighbors  
    G' = G - {G[0]} - neighbors(G[0])
    #find the max independent set of the remaining graph
    S1 = max_independent_set(G')
    #skip the first node and find the max independent set of the remaining graph
    S2 = max_independent_set(G[1:])
    #return the max of the two sets
    return max(S1 + G[0], S2)  
```
