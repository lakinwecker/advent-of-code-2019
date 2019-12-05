import System.Environment


main = do
    args <- getArgs
    putStrLn (args !! 0)
    content <- readFile (args !! 0)
    putStrLn "Problem 1"
