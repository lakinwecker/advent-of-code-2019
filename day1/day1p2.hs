import System.Environment

fuel :: Int -> Int
fuel m = (m `div` 3) - 2

toInt :: String -> Int
toInt s = read s :: Int

(|>) x f = f x

extraFuel :: Int -> Int
extraFuel m =
    let f = fuel(m)
    in if f <= 0 then 0 else (fuel(m) + extraFuel(f))


main = do
    args <- getArgs
    putStrLn (args !! 0)
    content <- readFile (args !! 0)
    -- I prefer this syntax (elm/f#) but meh
    content 
        |> lines
        |> map toInt
        |> map fuel
        |> sum
        |> (\m -> m + extraFuel m)
        |> show
        |> putStrLn
    -- putStrLn show $ sum $  map fuel $ map toInt $ lines content
