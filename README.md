This is a Graph pathfinding project I wrote for my Artificial Intelligence studies in Plovdiv University.

It uses DFS algorithm to find the possible routes in between the nodes.

The data about the nodes is stored in src/resources/nodes.grf and src/resources/links.grf. It's read in Graph::readData method when the Graph is initialized.

After the possible routes are found the shortest is selected among them and printed.

The searchRoute method accepts a List of strings with the destinations where the first one is the starting point, the last one is the final and the ones in between - nodes that should be part of the route
