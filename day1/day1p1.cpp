#include <iostream>
#include <sstream>
#include <vector>
#include <iterator>
#include <algorithm>
#include <numeric>

using namespace std;

int calcFuel(int mass) {
    return mass / 3 - 2;
}

int main() {
    vector<int> items;
    copy(istream_iterator<int>(cin), istream_iterator<int>(), back_inserter(items));
    transform(items.begin(), items.end(), items.begin(), calcFuel);
    cout << accumulate(items.begin(), items.end(), 0) << endl;
}
