#include <iostream>
#include <sstream>
#include <vector>
#include <iterator>
#include <algorithm>
#include <numeric>

using namespace std;

int fuel(int mass) {
    return mass / 3 - 2;
}

int extraFuel(int mass) {
    int f = fuel(mass);
    if (f <= 0) {
        return 0;
    } else {
        return f + extraFuel(f);
    }
}

int main() {
    vector<int> items;
    copy(istream_iterator<int>(cin), istream_iterator<int>(), back_inserter(items));
    transform(items.begin(), items.end(), items.begin(), fuel);
    auto subTotal = accumulate(items.begin(), items.end(), 0);
    auto total = subTotal + extraFuel(subTotal);
    cout << total << endl;
}
