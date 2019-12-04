import System.Environment

fuel :: Int -> Int
fuel m = 
    let f = (m `div` 3) - 2
    in 
        if f <= 0 then 0 else f + fuel(f)

toInt :: String -> Int
toInt s = read s :: Int

(|>) x f = f x

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
        |> show
        |> putStrLn
    -- putStrLn show $ sum $  map fuel $ map toInt $ lines content
