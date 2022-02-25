# y = mx + b
# Bo -> b
# B1 -> m
# x1 -> x

# y = Bo + B1*X1

library(ggplot2)
library(ggforfity)

x=c(2, 1, 3, 5, 4)
y=c(4, 2, 6, 10, 8)

puntos = as.data.frame(cbind(x, y))
ggplot(puntos.aes(x=x, y=y)) + 
geon_point(size = 2, shape = 8, color = "red") +
ggtitle("first graficph")