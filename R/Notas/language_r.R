# Tipos de datos y asignaciones
var = T
print(var)
print(class(var))
var <- F
print(var)
print(class(var))
var <- 20L
print(var)
print(class(var))
var <- 23.6
print(var)
print(class(var))
var <- 42 + 8i
print(var)
print(class(var))
var = "Hello word"
print(var)
print(class(var))

# Operaciones basicas

var_1 = T
var_2 <- 42.3
var_3 <- 8

print(var_1 + var_2)
print(var_1 - var_2)
print(var_2 %/% var_2)
print(var_2 %% var_2)

# Estruturas de datos basicas de R

# ARRAY

vector <- c(1, 2, 3, 4)
# print(vector)

# Names() nos permite dar nombre a
# datos de vector
# 
# Esto genera algo similar a una
# tabla o a una serie de python
# de la siguiente manera
#
# X Y Z     Esto es más similar a
# 1 2 3     un vector normal con
# nombres de dimensiones n del array

names(vector) <- c("Dato_1", "Dato_2", "Dato_3", "Dato_4")
print(vector)

# Acá los array no empiezan en 0 sino en 1

# print(vector[1])
# print(vector[4])

# Asignación de label a vectores

nombre <- c("jose", "andres", "bob")
alturas <- c(1.23, 1.73, 1.20)
edades <- c(12, 18, 24)

names(alturas) <- nombre
names(edades) <- nombre


# print(alturas)
# print(edades)

# print(alturas + edades)
# print(alturas - edades)
# print(alturas * edades)
# print(alturas / edades)
# print(alturas %% edades)
# print(alturas %*% edades)

# MATRICES

informacion <- matrix(data = c(edades, alturas), nrow = length(edades), ncol = length(edades), byrow = T)
# print(informacion)

vector_1 <- rep(1, length(edades))
# print(vector_1)

# DATAFRAMES

df <- data.frame(alturas, edades, row.names = nombre, check.rows = c(T, T, T))
# print(df)

# Funciones de un df

head(df)
tail(df)
print(str(df))

# print(df$alturas[1])
# print(df[df$alturas == 1.73,])

attach(df)
detach(df)

# listas

# print(?list())

planetas <- list("nombres" = c(" Mercurio", "venus", "tierra"), "tamaño" = "mediano")
# print(planetas)

iris$Species <- NULL
attach(iris)
# plot(iris)

# Libraries
# install.packages(rgl)
# library(rgl)

# iris$Species <- NULL
# attach(iris)
# open3d()
# plot3d(iris)
# play3d(spin3d(axis = c(0, 0, 1)))

mean(iris)
summary(iris)